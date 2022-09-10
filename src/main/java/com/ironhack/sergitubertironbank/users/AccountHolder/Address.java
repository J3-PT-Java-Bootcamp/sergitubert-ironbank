package com.ironhack.sergitubertironbank.users.AccountHolder;

import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String province;
    private String country;
    private Boolean isPrimaryAddress;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private AccountHolder user;

    public Address(String street, String streetNumber, String postalCode, String city, String province, String country, Boolean isPrimaryAddress, AccountHolder user) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
        this.isPrimaryAddress = isPrimaryAddress;
        this.user = user;
    }

    public static Address fromDto(CreateAddressDto dto, AccountHolder holder) {
        return new Address(dto.getStreet(), dto.getStreetNumber(), dto.getPostalCode(), dto.getCity(), dto.getProvince(), dto.getCountry(), dto.isPrimaryAddress(), holder);
    }

}
