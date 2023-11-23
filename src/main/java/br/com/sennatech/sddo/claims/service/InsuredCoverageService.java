package br.com.sennatech.sddo.claims.service;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.entity.*;
import br.com.sennatech.sddo.claims.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuredCoverageService {
    
    private final InsuredCoverageRepository repository;

    public InsuredCoverage retrieveFromCode(Long code) {
        return repository.findById(code).orElseThrow(() -> new EntityNotFoundException("Insured Coverage not found"));
    }
}
