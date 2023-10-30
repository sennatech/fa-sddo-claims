package br.com.sennatech.sddo.claims.domain.dto;

import lombok.Data;

@Data
public class InsuredAddressDTO {
    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private Long zipcode;
}
