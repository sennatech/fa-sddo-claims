package br.com.sennatech.sddo.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.claims.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
