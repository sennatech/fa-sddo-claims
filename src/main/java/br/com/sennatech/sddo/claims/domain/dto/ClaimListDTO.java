package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.sennatech.sddo.claims.domain.enums.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimListDTO {
    private String claimId;
    private String notifierDocument;
    private String insuredDocument;
    private Status status;
    private LocalDate date;
    private LocalDate notificationDate;
}
