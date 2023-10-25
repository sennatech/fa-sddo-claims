package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import br.com.sennatech.sddo.claims.domain.enums.Gender;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String documentNumber;
    private String name;
    private LocalDate birthdate;
    private Gender gender;
    private String login;
    private String password;
    private String email;
    private String areaCode;
    private String phone;
}
