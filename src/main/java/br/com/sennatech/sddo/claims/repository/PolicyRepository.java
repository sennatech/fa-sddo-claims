package br.com.sennatech.sddo.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sennatech.sddo.claims.domain.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
