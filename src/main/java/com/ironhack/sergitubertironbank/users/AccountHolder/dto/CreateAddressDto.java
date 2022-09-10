package com.ironhack.sergitubertironbank.users.AccountHolder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class CreateAddressDto {

    @NotEmpty
    private Long userId;

    @NotEmpty
    private String street;

    @NotEmpty
    private String streetNumber;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String city;

    @NotEmpty
    private String province;

    @NotEmpty
    private String country;

    @NotEmpty
    private boolean isPrimaryAddress;
}
