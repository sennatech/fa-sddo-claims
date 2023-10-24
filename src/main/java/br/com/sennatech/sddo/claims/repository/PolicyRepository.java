package br.com.sennatech.sddo.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sennatech.sddo.claims.domain.entity.Customer;
import br.com.sennatech.sddo.claims.domain.entity.Policy;
import java.util.List;


@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByCustomer(Customer customer);
}
