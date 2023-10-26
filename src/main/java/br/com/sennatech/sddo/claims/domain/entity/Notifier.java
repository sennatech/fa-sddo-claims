package br.com.sennatech.sddo.claims.domain.entity;

import java.time.LocalDate;

import br.com.sennatech.sddo.claims.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifier {
  @Id
  @Column(nullable = false, updatable = false, length = 11)
  private String documentNumber;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private LocalDate birthdate;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;
  @Column(nullable = false)
  private String email;
}
