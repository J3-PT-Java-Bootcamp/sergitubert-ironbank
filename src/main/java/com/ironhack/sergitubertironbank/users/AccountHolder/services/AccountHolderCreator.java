package com.ironhack.sergitubertironbank.users.AccountHolder.services;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;

public class AccountHolderCreator {
    private final AccountHolderRepository repository;

    public AccountHolderCreator(AccountHolderRepository repository) {
        this.repository = repository;
    }

    public AccountHolder execute(CreateAccountHolderDto dto) {
        // TODO: Create AccountHolder in keycloak
        var holder = AccountHolder.fromDto(dto);
        return this.repository.save(holder);
    }
}
