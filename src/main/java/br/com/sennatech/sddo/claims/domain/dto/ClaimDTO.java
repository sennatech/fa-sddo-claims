package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO {
  private String description;
  private LocalDate date;
  private String insuredDocument;
  private NotificationAddressDTO address;
  private Long policyNumber;
  private NotifierDTO notifier;
  private Long coverageCode;
}
