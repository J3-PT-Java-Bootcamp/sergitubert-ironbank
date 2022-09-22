package com.ironhack.sergitubertironbank.users.AccountHolder.services;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.exceptions.AccountHolderNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class AccountHolderFinder {
    private final AccountHolderRepository repository;

    public AccountHolderFinder(AccountHolderRepository repository) {
        this.repository = repository;
    }

    public AccountHolder execute(String keycloakId) throws AccountHolderNotFoundException {
        return this.repository.findAccountHolderByKeycloakId(keycloakId).orElseThrow(() -> new AccountHolderNotFoundException(keycloakId));
    }
}
