package br.com.sennatech.sddo.claims.function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.ClaimDetailsDTO;
import br.com.sennatech.sddo.claims.domain.entity.*;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClaimToClaimDetailsDTO {

    private final CoverageToCoverageDTO coverageToCoverageDTO;
    private final CustomerToInsuredDTO customerToInsuredDTO;
    private final NotifierToNotifierDTO notifierToNotifierDTO;
    private final InsuredAddressToInsuredAddressDTO insuredAddressToInsuredAddressDTO;

    public ClaimDetailsDTO apply(Claim claim, Coverage coverage, Notifier notifier, InsuredAddress insuredAddress, Customer customer) {
        return ClaimDetailsDTO.builder()
        .coverage(coverageToCoverageDTO.apply(coverage))
        .date(claim.getDate())
        .description(claim.getDescription())
        .insured(customerToInsuredDTO.apply(customer))
        .insuredAddress(insuredAddressToInsuredAddressDTO.apply(insuredAddress))
        .notificationDate(null)
        .notifier(notifierToNotifierDTO.apply(notifier))
        .policyNumber(claim.getPolicy())
        .build();
    }
}
