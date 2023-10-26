package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO {
  private String description;
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate date;
  private String insuredDocument;
  private NotificationAddressDTO address;
  private Long policyNumber;
  private NotifierDTO notifier;
  private Long coverageCode;
}
