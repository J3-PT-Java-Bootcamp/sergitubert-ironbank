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

    public AccountHolder execute(Long id) throws AccountHolderNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new AccountHolderNotFoundException(id));
    }
}
