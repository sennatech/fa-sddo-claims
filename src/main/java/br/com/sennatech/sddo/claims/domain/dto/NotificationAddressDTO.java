package br.com.sennatech.sddo.claims.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationAddressDTO {
    private Long id;
    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private Long zipcode;
    private String country;
}
