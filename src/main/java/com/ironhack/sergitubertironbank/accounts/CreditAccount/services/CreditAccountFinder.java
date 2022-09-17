package com.ironhack.sergitubertironbank.accounts.CreditAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class CreditAccountFinder {
    private final CreditAccountRepository repository;

    public CreditAccountFinder(CreditAccountRepository repository) {
        this.repository = repository;
    }

    public CreditAccount execute(String iban) throws AccountNotFoundException {
        return this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
    }
}
