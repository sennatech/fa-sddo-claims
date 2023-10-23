package br.com.sennatech.sddo.claims.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "policy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    private Long number;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate validityStart;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate validityEnd;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;
    @OneToOne
    @JoinColumn(name = "holder_document", nullable = false)
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;
}
