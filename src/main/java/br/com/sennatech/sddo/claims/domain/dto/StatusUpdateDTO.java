package br.com.sennatech.sddo.claims.domain.dto;

import br.com.sennatech.sddo.claims.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateDTO {
    private Status status;
}
