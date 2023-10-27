package br.com.sennatech.sddo.claims.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "insured_address")
@Data
public class InsuredAddress {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, length = 100)
    private String street;
    @Column(nullable = false, length = 4)
    private String number;
    @Column(nullable = false, length = 100)
    private String neighbourhood;
    @Column(nullable = false, length = 30)
    private String city;
    @Column(nullable = false, length = 2)
    private String state;
    @Column(nullable = false)
    private Long areaCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "policy", nullable = false)
    private Policy policy;
    @Column(nullable = false, length = 20)
    private String country;
}
