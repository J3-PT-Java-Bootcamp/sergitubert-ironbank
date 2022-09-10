package com.ironhack.sergitubertironbank.users.AccountHolder;

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
    @Column(name = "id", nullable = false)
    private Long id;

    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String province;
    private String country;
    private boolean isPrimaryAddress;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private AccountHolder user;

}
