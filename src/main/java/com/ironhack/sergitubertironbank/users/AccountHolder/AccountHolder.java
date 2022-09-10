package com.ironhack.sergitubertironbank.users.AccountHolder;

import com.ironhack.sergitubertironbank.users.shared.BaseUser;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends BaseUser {
    private LocalDate dateOfBirth;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

}
