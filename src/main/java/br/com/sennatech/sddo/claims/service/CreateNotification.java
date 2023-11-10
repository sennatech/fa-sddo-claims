package br.com.sennatech.sddo.claims.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.ClaimDTO;
import br.com.sennatech.sddo.claims.domain.entity.InsuredAddress;
import br.com.sennatech.sddo.claims.domain.entity.Claim;
import br.com.sennatech.sddo.claims.domain.entity.Policy;
import br.com.sennatech.sddo.claims.domain.enums.Status;
import br.com.sennatech.sddo.claims.exception.ExpiredPolicyException;
import br.com.sennatech.sddo.claims.exception.InvalidNotificationAddressException;
import br.com.sennatech.sddo.claims.function.ClaimDTOtoClaim;
import br.com.sennatech.sddo.claims.repository.ClaimRepository;
import br.com.sennatech.sddo.claims.repository.InsuredAddressRepository;
import br.com.sennatech.sddo.claims.repository.NotifierRepository;
import br.com.sennatech.sddo.claims.repository.PolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CreateNotification {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private NotifierRepository notifierRepository;

    @Autowired
    private InsuredAddressRepository insuredAddressRepository;

    @Autowired
    private ClaimDTOtoClaim converter;

    @Transactional
    public void run(ClaimDTO claimDTO) {
        Claim claim = converter.apply(claimDTO);
        String notifierDocument = claim.getNotifier().getDocumentNumber();
        if (notifierRepository.existsById(notifierDocument))
            claim.setNotifier(notifierRepository.getReferenceById(notifierDocument));
        Long zipcode = claim.getNotificationAddress().getZipcode();
        Policy policy = policyRepository.findById(claim.getPolicy())
                .orElseThrow(() -> new EntityNotFoundException("Policy not found"));
        InsuredAddress insuredAddress = insuredAddressRepository.findByPolicy(policy)
                .orElseThrow(() -> new EntityNotFoundException("Invalid insured address"));
        if (claim.getDate().isAfter(policy.getValidityEnd()) || !zipcode.equals(insuredAddress.getZipcode()))
            claim.setStatus(Status.RECUSADO);
        claimRepository.save(claim);
    }
}
