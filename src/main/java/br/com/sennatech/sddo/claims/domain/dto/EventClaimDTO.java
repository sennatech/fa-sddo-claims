package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.com.sennatech.sddo.claims.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventClaimDTO {
  private Long id;
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate date;
  private String description;
  private Type type;
  private NotifierDTO notifier;
  private Status status;
  private Long coverageCode;
  private String insuredDocument;
  private NotificationAddressDTO notificationAddress;
  private Long policy;
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate notificationDate;
}
