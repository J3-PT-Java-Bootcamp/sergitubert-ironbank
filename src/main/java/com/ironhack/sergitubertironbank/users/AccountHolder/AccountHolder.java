package com.ironhack.sergitubertironbank.users.AccountHolder;

import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;
import com.ironhack.sergitubertironbank.users.shared.BaseUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends BaseUser {
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    protected AccountHolder() {
        super();
    }

    public AccountHolder(String name, LocalDate dateOfBirth, String email) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public static AccountHolder fromDto(CreateAccountHolderDto dto) {
        return new AccountHolder(dto.getName(), dto.getDateOfBirth(), dto.getEmail());
    }
}
