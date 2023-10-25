package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.ClaimDTO;
import br.com.sennatech.sddo.claims.domain.entity.Claim;
import br.com.sennatech.sddo.claims.domain.enums.Status;
import br.com.sennatech.sddo.claims.domain.enums.Type;

@Component
public class ClaimDTOtoClaim implements Function<ClaimDTO, Claim> {

    @Autowired
    private NotifierDTOtoNotifier notifierDTOtoNotifier;

    @Autowired
    private NotificationAddressDTOtoNotificationAdress notificationAddressDTOtoNotificationAdress;

    @Override
    public Claim apply(ClaimDTO claimDTO) {
        return Claim.builder()
            .description(claimDTO.getDescription())
            .date(claimDTO.getDate())
            .notificationAddress(notificationAddressDTOtoNotificationAdress.apply(claimDTO.getAddress()))
            .coverageCode(claimDTO.getCoverageCode())
            .insuredDocument(claimDTO.getInsuredDocument())
            .notifier(notifierDTOtoNotifier.apply(claimDTO.getNotifier()))
            .policy(claimDTO.getPolicyNumber())
            .status(Status.PENDENTE)
            .type(Type.RESIDENCIAL)
            .build();
    }
}
