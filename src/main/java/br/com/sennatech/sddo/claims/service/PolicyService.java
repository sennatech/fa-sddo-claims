package br.com.sennatech.sddo.claims.service;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.entity.Policy;
import br.com.sennatech.sddo.claims.repository.PolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

    public Policy retrieveFromNumber(Long number) {
        return policyRepository.findById(number).orElseThrow(() -> new EntityNotFoundException("Policy not found"));
    }
}
