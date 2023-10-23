package br.com.sennatech.sddo.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sennatech.sddo.claims.domain.entity.Document;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
  Optional<Document> findByClaim(Long claim);
}
