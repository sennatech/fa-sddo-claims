package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;
import br.com.sennatech.sddo.claims.domain.enums.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO {
  private String id;
  private LocalDate date;
  private String description;
  private String type;
  private Long notifierDocument;
  private Status status;
  private Long coverage;
  private Long notificationAddress;
  private Long policy;
  private LocalDate notificationDate;
}
