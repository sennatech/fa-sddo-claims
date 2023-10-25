package br.com.sennatech.sddo.claims.domain.dto;
import java.sql.Blob;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private Long id;
    private Blob file;
    private String name;
    private String claimId;
}
