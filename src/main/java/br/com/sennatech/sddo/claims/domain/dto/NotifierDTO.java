package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import br.com.sennatech.sddo.claims.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifierDTO {
  private String documentNumber;
  private String name;
  private LocalDate birthdate;
  private Gender gender;
  private String email;
}
