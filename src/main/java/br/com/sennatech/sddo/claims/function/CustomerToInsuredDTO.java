package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.InsuredDTO;
import br.com.sennatech.sddo.claims.domain.entity.Customer;

@Component
public class CustomerToInsuredDTO implements Function<Customer, InsuredDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public InsuredDTO apply(Customer customer) {
        return mapper.map(customer, InsuredDTO.class);
    }
}
