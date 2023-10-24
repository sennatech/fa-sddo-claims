package br.com.sennatech.sddo.claims.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coverage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coverage {
    @Id
    @Column(nullable = false, length = 11)
    private String documentNumber;
    
    @Column(nullable = false, length = 100)
    private String description;

    // TODO: #2 Change property name to "Amount" in Coverages entity
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sumInsured;

    @Column(nullable = false)
    private Boolean required;
}
