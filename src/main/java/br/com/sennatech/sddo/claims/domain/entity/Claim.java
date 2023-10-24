package br.com.sennatech.sddo.claims.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import br.com.sennatech.sddo.claims.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "claim")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
  @Id
  @Column(nullable = false, unique = true, updatable = false)
  private String id;

  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false, length = 20)
  private String type;

  @OneToOne
  @JoinColumn(name = "notifier_document", nullable = false)
  private Notifier notifier;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @OneToOne
  @JoinColumn(name = "coverage", nullable = false)
  private Coverage coverage;

  @Column(nullable = false, length = 11)
  private String insuredDocument;

  @OneToOne
  @JoinColumn(name = "notification_address", nullable = false)
  private NotificationAddress notificationAddress;

  @OneToOne
  @JoinColumn(name = "policy", nullable = false)
  private Policy policy;

  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  private LocalDate notificationDate;
}
