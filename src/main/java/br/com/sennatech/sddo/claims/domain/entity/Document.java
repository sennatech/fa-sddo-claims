package br.com.sennatech.sddo.claims.domain.entity;

import java.sql.Blob;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(nullable = false)
    private Blob file;
    @Column(nullable = false)
    private String name;
    @JoinColumn(name = "claim", nullable = false)
    @ManyToOne
    private Claim claim;

}
