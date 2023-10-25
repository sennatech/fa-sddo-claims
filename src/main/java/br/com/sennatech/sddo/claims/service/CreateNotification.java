package br.com.sennatech.sddo.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.ClaimDTO;
import br.com.sennatech.sddo.claims.function.ClaimDTOtoClaim;
import br.com.sennatech.sddo.claims.repository.ClaimRepository;

@Service
public class CreateNotification {

    @Autowired
    private ClaimRepository repository;

    @Autowired
    private ClaimDTOtoClaim converter;

    public void run(ClaimDTO claimDTO) {
        repository.save(converter.apply(claimDTO));
    }
}
