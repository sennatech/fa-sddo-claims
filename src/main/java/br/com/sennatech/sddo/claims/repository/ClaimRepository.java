package br.com.sennatech.sddo.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sennatech.sddo.claims.domain.entity.*;

import java.util.List;
import br.com.sennatech.sddo.claims.domain.enums.Status;


@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByStatus(Status status);
    List<Claim> findByNotifier(Notifier notifier);
    List<Claim> findByInsuredDocument(String insuredDocument);
}
