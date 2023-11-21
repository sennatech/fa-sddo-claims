package br.com.sennatech.sddo.claims.function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.EventClaimStatusDTO;
import br.com.sennatech.sddo.claims.domain.entity.*;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClaimToEventClaimStatusDTO {

    private final NotifierToNotifierDTO notifierToNotifierDTO;
    private final NotificationAddressToNotificationAdressDTO notificationAddressToNotificationAdressDTO;

    public EventClaimStatusDTO apply(Claim claim, Double paidAmount) {
        return EventClaimStatusDTO.builder()
        .coverageCode(claim.getCoverageCode())
                .date(claim.getDate())
        .paidAmount(paidAmount)
        .description(claim.getDescription())
        .insuredDocument(claim.getInsuredDocument())
        .notificationAddress(notificationAddressToNotificationAdressDTO.apply(claim.getNotificationAddress()))
        .notificationDate(claim.getNotificationDate())
        .notifier(notifierToNotifierDTO.apply(claim.getNotifier()))
        .policy(claim.getPolicy())
        .id(claim.getId())
        .status(claim.getStatus())
        .type(claim.getType())
        .build();
    }
}
