package br.com.sennatech.sddo.claims.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hired_coverages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuredCoverage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, updatable = false, nullable = false)
  private Long id;

  @Column(nullable = false)
  private Long policyNumber;

  @Column(nullable = false)
  private Long coverageId;

  @Column(nullable = false)
  private Double hiredValue;
}
