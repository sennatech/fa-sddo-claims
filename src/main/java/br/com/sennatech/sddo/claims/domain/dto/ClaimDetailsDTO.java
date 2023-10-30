package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimDetailsDTO {
  private String description;
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate date;
  private LocalDate notificationDate;
  private Long policyNumber;
  private InsuredDTO insured;
  private NotifierDTO notifier;
  private CoverageDTO coverage;
  private InsuredAddressDTO insuredAddress;
}
