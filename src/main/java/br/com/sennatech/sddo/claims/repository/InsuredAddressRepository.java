package br.com.sennatech.sddo.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.claims.domain.entity.InsuredAddress;
import br.com.sennatech.sddo.claims.domain.entity.Policy;

public interface InsuredAddressRepository extends JpaRepository<InsuredAddress, Long> {
    InsuredAddress getReferenceByPolicy(Policy policy);
}
