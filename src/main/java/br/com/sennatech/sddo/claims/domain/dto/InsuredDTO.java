package br.com.sennatech.sddo.claims.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuredDTO {
    private String documentNumber;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String email;
}
