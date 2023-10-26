package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import br.com.sennatech.sddo.claims.domain.dto.ClaimListDTO;
import br.com.sennatech.sddo.claims.domain.entity.Claim;

@Component
public class ClaimToClaimListDTO implements Function<Claim, ClaimListDTO> {

    @Override
    public ClaimListDTO apply(Claim claim) {
        return ClaimListDTO.builder()
        .claimId("CLA-" + claim.getId())    
        .date(claim.getDate())
        .insuredDocument(claim.getInsuredDocument())
        .notificationDate(claim.getNotificationDate())
        .notifierDocument(claim.getNotifier().getDocumentNumber())
        .status(claim.getStatus())
        .build();
    }
}
