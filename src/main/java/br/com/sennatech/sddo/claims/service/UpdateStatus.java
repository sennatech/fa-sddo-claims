package br.com.sennatech.sddo.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.entity.Claim;
import br.com.sennatech.sddo.claims.domain.enums.Status;
import br.com.sennatech.sddo.claims.repository.ClaimRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UpdateStatus {

    @Autowired
    private ClaimRepository repository;

    public void run(String claimIdString, Status newStatus) {
        if (newStatus.equals(Status.PENDENTE)) throw new IllegalArgumentException("Can't set an already created status to pending");
        Long claimId = Long.valueOf((claimIdString.contains("CLA-")) ? claimIdString.replace("CLA-", "") : claimIdString);
        Claim claim = repository.findById(claimId).orElseThrow(() -> new EntityNotFoundException("Claim not found"));
        claim.setStatus(newStatus);
        repository.save(claim);
    }
}
