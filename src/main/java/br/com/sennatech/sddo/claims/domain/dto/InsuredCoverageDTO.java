package br.com.sennatech.sddo.claims.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuredCoverageDTO {
  private Long id;
  private Long policyNumber;
  private Long coverageId;
  private Double hiredValue;
}
