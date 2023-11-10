package br.com.sennatech.sddo.claims.service;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.entity.Coverage;
import br.com.sennatech.sddo.claims.repository.CoverageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverageService {
    
    private final CoverageRepository coverageRepository;

    public Coverage retrieveFromCode(Long code) {
        return coverageRepository.findById(code).orElseThrow(() -> new EntityNotFoundException("Coverage not found"));
    }
}
