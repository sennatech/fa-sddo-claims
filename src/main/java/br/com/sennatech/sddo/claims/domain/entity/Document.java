package br.com.sennatech.sddo.claims.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`documents`")
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
    @Column(name = "`file`", nullable = false)
    private Byte[] file;
    @Column(nullable = false)
    private String name;
    @Builder.Default
    @JoinColumn(name = "claim_id")
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Claim> claim = new ArrayList<>();
}
