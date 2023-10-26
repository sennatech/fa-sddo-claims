package br.com.sennatech.sddo.claims.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDTO {
    private Long number;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validityStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityEnd;
    private BigDecimal amount;
    private CustomerDTO customer;
    private PaymentDTO payment;
}
