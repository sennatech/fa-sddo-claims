package br.com.sennatech.sddo.claims.domain.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import br.com.sennatech.sddo.claims.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "claims")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
  @Id
  @GeneratedValue
  @Column(nullable = false, unique = true, updatable = false)
  private Long id;
  @Temporal(TemporalType.DATE)
  @Column(nullable = false, columnDefinition = "DATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Type type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "notifier_document", nullable = false)
  private Notifier notifier;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @Column(nullable = false)
  private Long coverageCode;

  @Column(nullable = false, length = 11)
  private String insuredDocument;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "notification_address", nullable = false)
  private NotificationAddress notificationAddress;

  @Column(nullable = false)
  private Long policy;

  @Temporal(TemporalType.DATE)
  @Column(nullable = false, columnDefinition = "DATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate notificationDate;
}
