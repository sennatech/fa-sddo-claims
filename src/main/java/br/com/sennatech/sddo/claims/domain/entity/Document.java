package br.com.sennatech.sddo.claims.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @Lob
    @Column(nullable = false)
    private Byte[] file;
    @Column(nullable = false)
    private String name;
    @JoinColumn(name = "claimId", nullable = false)
    @OneToOne
    private Claim claim;
}
