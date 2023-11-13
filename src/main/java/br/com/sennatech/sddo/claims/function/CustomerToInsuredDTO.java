package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.InsuredDTO;
import br.com.sennatech.sddo.claims.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerToInsuredDTO implements Function<Customer, InsuredDTO> {

    private final ModelMapper mapper;

    @Override
    public InsuredDTO apply(Customer customer) {
        return mapper.map(customer, InsuredDTO.class);
    }
}
