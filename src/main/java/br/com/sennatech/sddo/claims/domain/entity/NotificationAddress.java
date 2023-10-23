package br.com.sennatech.sddo.claims.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationAddress {
    @Id
    @Column(nullable = false, updatable = false, unique = true)
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
    private Long zipcode;
    @Column(nullable = false, length = 20)
    private String country;
}
