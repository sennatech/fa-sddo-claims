package br.com.sennatech.sddo.claims.domain.dto;

import br.com.sennatech.sddo.claims.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoverageDTO {
  private Long code;
  private String name;
  private String description;
  private Type type;
}
