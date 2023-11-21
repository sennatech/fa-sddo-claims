package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuredDTO {
    private String documentNumber;
    private String name;
    private LocalDate birthdate;
    private String email;
}
