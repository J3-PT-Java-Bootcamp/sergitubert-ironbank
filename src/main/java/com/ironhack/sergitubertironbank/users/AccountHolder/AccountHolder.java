package com.ironhack.sergitubertironbank.users.AccountHolder;

import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;
import com.ironhack.sergitubertironbank.users.shared.BaseUser;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends BaseUser {
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    protected AccountHolder() {
        super();
    }

    public AccountHolder(String name, LocalDate dateOfBirth, String email) {
        super(name, email);
        this.dateOfBirth = dateOfBirth;
    }

    public static AccountHolder fromDto(CreateAccountHolderDto dto) {
        return new AccountHolder(dto.getName(), dto.getDateOfBirth(), dto.getEmail());
    }
}
