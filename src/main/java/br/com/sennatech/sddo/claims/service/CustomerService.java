package br.com.sennatech.sddo.claims.service;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.entity.Customer;
import br.com.sennatech.sddo.claims.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    public Customer retrieveFromDocumentNumber(String documentNumber) {
        return customerRepository.findById(documentNumber).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}
