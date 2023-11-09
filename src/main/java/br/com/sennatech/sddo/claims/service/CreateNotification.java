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
        if (notifierRepository.existsById(notifierDocument)) claim.setNotifier(notifierRepository.getReferenceById(notifierDocument));
        Long policyNumber = claim.getPolicy();
        Long zipcode = claim.getNotificationAddress().getZipcode();
        LocalDate date = claim.getDate();
        try {
            Policy policy = policyRepository.getReferenceById(policyNumber);
            InsuredAddress insuredAddress = insuredAddressRepository.getReferenceByPolicy(policy);
            if (date.isAfter(policy.getValidityEnd()))
                throw new ExpiredPolicyException("Occurency date is after policy validity end");
            if (!zipcode.equals(insuredAddress.getZipcode()))
                throw new InvalidNotificationAddressException(
                        "Notification address zipcode different from insured address zipcode from specified policy");
        } catch (Exception e) {
            claim.setStatus(Status.RECUSADO);
        }
        claimRepository.save(claim);
    }
}
