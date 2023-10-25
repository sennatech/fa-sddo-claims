package br.com.sennatech.sddo.claims.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String transaction;
    private BigDecimal value;
}
