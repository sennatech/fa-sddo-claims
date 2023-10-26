package br.com.sennatech.sddo.claims.domain.dto;

import br.com.sennatech.sddo.claims.domain.enums.Status;
import lombok.Data;

@Data
public class StatusUpdateDTO {
    private Status status;
}
