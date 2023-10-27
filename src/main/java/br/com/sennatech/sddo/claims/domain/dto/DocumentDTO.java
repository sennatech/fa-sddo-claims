package br.com.sennatech.sddo.claims.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private Long id;
    private Byte[] file;
    private String name;
    private String claimId;
}
