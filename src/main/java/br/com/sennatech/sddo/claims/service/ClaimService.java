package br.com.sennatech.sddo.claims.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.*;
import br.com.sennatech.sddo.claims.domain.entity.*;
import br.com.sennatech.sddo.claims.domain.enums.*;
import br.com.sennatech.sddo.claims.function.*;
import br.com.sennatech.sddo.claims.repository.*;
import br.com.sennatech.sddo.claims.util.TransformationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaimService {

    //services
    private final ClaimRepository claimRepository;
    private final PolicyService policyService;
    private final CoverageService coverageService;
    private final NotifierService notifierService;
    private final InsuredAddressService insuredAddressService;
    private final CustomerService customerService;

    //converters
    private final ClaimToClaimListDTO claimToClaimListDTO;
    private final ClaimDTOtoClaim claimDTOtoClaim;
    private final ClaimToClaimDetailsDTO claimToClaimDetailsDTO;

    //util
    private final List<String> autoRefusalReasons = new ArrayList<>();

    public List<String> getAutoRefusalReasons() {
        return autoRefusalReasons;
    }

    public void create(ClaimDTO claimDTO) {
        Claim claim = claimDTOtoClaim.apply(claimDTO);
        Notifier notifier = notifierService.retrieveOrCreateNotifier(claim.getNotifier().getDocumentNumber(),
                claimDTO.getNotifier());
        claim.setNotifier(notifier);
        checkConstraints(claim);
        claimRepository.saveAndFlush(claim);
    }

    public ClaimDetailsDTO retrieveFromClaimId(String claimId) {
        Claim claim = retrieveFromId(TransformationUtil.claimIdToLong(claimId));
        Policy policy = policyService.retrieveFromNumber(claim.getPolicy());
        InsuredAddress insuredAddress = insuredAddressService.retrieveFromPolicy(policy);
        Coverage coverage = coverageService.retrieveFromCode(claim.getCoverageCode());
        Notifier notifier = claim.getNotifier();
        Customer customer = customerService.retrieveFromDocumentNumber(claim.getInsuredDocument());
        return claimToClaimDetailsDTO.apply(claim, coverage, notifier, insuredAddress, customer);
    }

    public Claim updateStatus(String claimId, Status newStatus) {
        if (newStatus == Status.PENDENTE) {
            throw new IllegalArgumentException("Cannot set an already created status to pending");
        }
        long id = TransformationUtil.claimIdToLong(claimId);
        Claim claim = retrieveFromId(id);
        if (claim.getStatus() == newStatus) {
            throw new IllegalArgumentException("The status is already set to the provided status");
        }
        claim.setStatus(newStatus);
        return claimRepository.save(claim);
    }

    public List<ClaimListDTO> list(Map<String, String> queryParameters) throws IllegalArgumentException {
        Optional<String> stringStatus = Optional.ofNullable(queryParameters.get("status"));
        Optional<String> insuredDocument = Optional.ofNullable(queryParameters.get("insuredDocument"));
        Optional<String> notifierDocument = Optional.ofNullable(queryParameters.get("notifierDocument"));

        List<Claim> listOfAllClaims = retrieveClaims(insuredDocument, notifierDocument);

        Stream<Claim> claimStream = listOfAllClaims.stream();

        if (stringStatus.isPresent()) {
            final String status = stringStatus.get();
            claimStream = claimStream.filter(claim -> claim.getStatus().equals(Status.fromString(status)));
        }
        return claimStream.map(claimToClaimListDTO::apply)
                         .collect(Collectors.toList());
    }

    private List<Claim> retrieveClaims(Optional<String> insuredDocument, Optional<String> notifierDocument) {
        if (notifierDocument.isPresent()) {
            return retrieveListFromNotifierDocument(notifierDocument.get());
        } else if (insuredDocument.isPresent()) {
            return retriveListFromInsuredDocument(insuredDocument.get());
        } else {
            return claimRepository.findAll();
        }
    }

    private List<Claim> retriveListFromInsuredDocument(String insuredDocument) {
        return claimRepository.findByInsuredDocument(insuredDocument);
    }

    private List<Claim> retrieveListFromNotifierDocument(String notifierDocument) {
        var notifier = notifierService.retrieveFromDocumentNumber(notifierDocument);
        return claimRepository.findByNotifier(notifier);
    }

    public Claim retrieveFromId(Long id) {
        return claimRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Claim not found"));
    }

    private void checkConstraints(Claim claim) {
        Policy policy = policyService.retrieveFromNumber(claim.getPolicy());
        InsuredAddress insuredAddress = insuredAddressService.retrieveFromPolicy(policy);
        Boolean refused = false;
        if (claim.getDate().isAfter(policy.getValidityEnd())) {
            refused = true;
            autoRefusalReasons.add("Notification date is after policy validity end");
        }
        if (!claim.getNotificationAddress().getZipcode().equals(insuredAddress.getZipcode())) {
            refused = true;
            autoRefusalReasons.add("Notification zipcode is different from insured address zipcode");
        }
        if (Boolean.TRUE.equals(refused)) {
            claim.setStatus(Status.RECUSADO);
        }
    }
}
