package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.CoverageDTO;
import br.com.sennatech.sddo.claims.domain.entity.Coverage;

@Component
public class CoverageToCoverageDTO implements Function<Coverage, CoverageDTO> {

    @Override
    public CoverageDTO apply(Coverage coverage) {
        return CoverageDTO.builder()
        .code(coverage.getId())
        .description(coverage.getDescription())
        .name(coverage.getName())
        .type(coverage.getType())
        .build();
    }
}
