package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.ClaimDTO;
import br.com.sennatech.sddo.claims.domain.entity.Claim;

@Component
public class ClaimToClaimDTO implements Function<Claim, ClaimDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public ClaimDTO apply(Claim claim) {
        return mapper.map(claim, ClaimDTO.class);
    }
}
