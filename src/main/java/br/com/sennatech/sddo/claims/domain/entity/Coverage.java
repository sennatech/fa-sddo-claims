package br.com.sennatech.sddo.claims.domain.entity;

import br.com.sennatech.sddo.claims.domain.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coverages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coverage {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, updatable = false, nullable = false)
  private Long id;

  @Column(unique = true, length = 50)
  private String name;

  @Column(length = 100, nullable = false)
  private String description;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Type type;

  @Column(nullable = false)
  private Double sumInsured;

  @Column(nullable = false)
  private Boolean required;
}
