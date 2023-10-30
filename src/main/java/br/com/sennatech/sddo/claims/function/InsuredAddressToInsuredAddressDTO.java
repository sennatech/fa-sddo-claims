package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.InsuredAddressDTO;
import br.com.sennatech.sddo.claims.domain.entity.InsuredAddress;

@Component
public class InsuredAddressToInsuredAddressDTO implements Function<InsuredAddress, InsuredAddressDTO> {
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public InsuredAddressDTO apply(InsuredAddress insuredAddress) {
        return mapper.map(insuredAddress, InsuredAddressDTO.class);
    }
}
