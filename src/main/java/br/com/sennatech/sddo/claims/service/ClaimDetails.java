package br.com.sennatech.sddo.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.*;
import br.com.sennatech.sddo.claims.domain.entity.Policy;
import br.com.sennatech.sddo.claims.function.ClaimToClaimDetailsDTO;
import br.com.sennatech.sddo.claims.repository.*;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClaimDetails {

    @Autowired
    private ClaimRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PolicyRepository policyRepository;
    
    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private InsuredAddressRepository insuredAddressRepository;

    @Autowired
    private ClaimToClaimDetailsDTO converter;

    public ClaimDetailsDTO run(String claimIdString) {
        Long claimId = Long
                .valueOf((claimIdString.contains("CLA-")) ? claimIdString.replace("CLA-", "") : claimIdString);
        var claim = repository.findById(claimId).orElseThrow(() -> new EntityNotFoundException("Claim not found"));
        Policy policy = policyRepository.findById(claim.getPolicy()).orElseThrow(() -> new EntityNotFoundException("Policy not found"));
        var coverage = coverageRepository.findById(claim.getCoverageCode()).orElseThrow(() -> new EntityNotFoundException("Coverage not found"));
        var notifier = claim.getNotifier();
        var insuredAddress = insuredAddressRepository.findByPolicy(policy).orElseThrow(() -> new EntityNotFoundException("Insured Address not found"));
        var customer = customerRepository.findById(claim.getInsuredDocument()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return converter.apply(claim, coverage, notifier, insuredAddress, customer);
    }
}
