package br.com.sennatech.sddo.claims.service;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.entity.InsuredAddress;
import br.com.sennatech.sddo.claims.domain.entity.Policy;
import br.com.sennatech.sddo.claims.repository.InsuredAddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuredAddressService {

    private final InsuredAddressRepository insuredAddressRepository;

    public InsuredAddress retrieveFromPolicy(Policy policy) {
        return insuredAddressRepository.findByPolicy(policy)
                .orElseThrow(() -> new EntityNotFoundException("Insured address not found"));
    }
}
