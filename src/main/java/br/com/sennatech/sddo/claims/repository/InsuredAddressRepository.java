package br.com.sennatech.sddo.claims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.claims.domain.entity.InsuredAddress;
import br.com.sennatech.sddo.claims.domain.entity.Policy;


public interface InsuredAddressRepository extends JpaRepository<InsuredAddress, Long> {
    Optional<InsuredAddress> findByPolicy(Policy policy);
    InsuredAddress getReferenceByPolicy(Policy policy);
}
