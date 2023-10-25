package br.com.sennatech.sddo.claims.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDTO {
    private Long number;
    private LocalDate validityStart;
    private LocalDate validityEnd;
    private BigDecimal amount;
    private CustomerDTO customer;
    private PaymentDTO payment;
}
