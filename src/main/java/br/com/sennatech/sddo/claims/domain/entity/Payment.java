package br.com.sennatech.sddo.claims.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false, name = "`transaction`")
    private String transaction;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}
