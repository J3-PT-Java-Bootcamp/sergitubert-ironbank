package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.CheckingAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class CheckingAccountFinder {

    private final CheckingAccountRepository repository;

    public CheckingAccountFinder(CheckingAccountRepository repository) {
        this.repository = repository;
    }

    public CheckingAccount execute(String iban) throws AccountNotFoundException {
        return this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
    }
}
