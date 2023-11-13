package br.com.sennatech.sddo.claims.service;

import java.util.*;
import java.util.stream.Collectors;
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
        var notifier = notifierService.retrieveOrCreateNotifier(claim.getNotifier().getDocumentNumber(),
                claimDTO.getNotifier());
        claim.setNotifier(notifier);
        checkConstraints(claim);
        claimRepository.saveAndFlush(claim);
    }

    public ClaimDetailsDTO retrieveFromClaimId(String claimId) {
        var claim = retrieveFromId(TransformationUtil.claimIdToLong(claimId));
        var policy = policyService.retrieveFromNumber(claim.getPolicy());
        var insuredAddress = insuredAddressService.retrieveFromPolicy(policy);
        var coverage = coverageService.retrieveFromCode(claim.getCoverageCode());
        var notifier = claim.getNotifier();
        var customer = customerService.retrieveFromDocumentNumber(claim.getInsuredDocument());
        return claimToClaimDetailsDTO.apply(claim, coverage, notifier, insuredAddress, customer);
    }
    
    public void updateStatus(String claimId, Status newStatus) {
        if (newStatus.equals(Status.PENDENTE))
            throw new IllegalArgumentException("Can't set an already created status to pending");
        var claim = retrieveFromId(TransformationUtil.claimIdToLong(claimId));
        claim.setStatus(newStatus);
        claimRepository.save(claim);
    }

    public List<ClaimListDTO> list(Map<String, String> queryParameters) throws IllegalArgumentException {
        List<Claim> listOfAllClaims = new ArrayList<>();
        String stringStatus = queryParameters.get("status");
        String insuredDocument = queryParameters.get("insuredDocument");
        String notifierDocument = queryParameters.get("notifierDocument");
        if (notifierDocument != null) {
            listOfAllClaims.addAll(retrieveFromNotifierDocument(notifierDocument));
        }
        if (insuredDocument != null && notifierDocument == null) {
            listOfAllClaims.addAll(retriveListFromInsuredDocument(insuredDocument));
        }
        if (insuredDocument == null && notifierDocument == null) {
            listOfAllClaims.addAll(claimRepository.findAll());
        }
        if (stringStatus != null) {
            return listOfAllClaims.stream().filter(claim -> claim.getStatus().equals(Status.fromString(stringStatus)))
                    .map(claimToClaimListDTO::apply).collect(Collectors.toList());
        }
        return listOfAllClaims.stream().map(claimToClaimListDTO::apply).collect(Collectors.toList());
    }

    private List<Claim> retriveListFromInsuredDocument(String insuredDocument) {
        return claimRepository.findByInsuredDocument(insuredDocument);
    }

    private List<Claim> retrieveFromNotifierDocument(String notifierDocument) {
        var notifier = notifierService.retrieveFromDocumentNumber(notifierDocument);
        return claimRepository.findByNotifier(notifier);
    }
    
    public Claim retrieveFromId(Long id) {
        return claimRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Claim not found"));
    }

    private void checkConstraints(Claim claim) {
        var policy = policyService.retrieveFromNumber(claim.getPolicy());
        var insuredAddress = insuredAddressService.retrieveFromPolicy(policy);
        if (claim.getDate().isAfter(policy.getValidityEnd())) {
            claim.setStatus(Status.RECUSADO);
            autoRefusalReasons.add("Notification date is after policy validity end");
        }
        if (!claim.getNotificationAddress().getZipcode().equals(insuredAddress.getZipcode())) {
            claim.setStatus(Status.RECUSADO);
            autoRefusalReasons.add("Notification zipcode is different from insured address zipcode");
        }
    }
}
