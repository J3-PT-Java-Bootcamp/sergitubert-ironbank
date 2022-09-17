package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.SavingsAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class SavingsAccountFinder {

    private final SavingsAccountRepository repository;

    public SavingsAccountFinder(SavingsAccountRepository repository) {
        this.repository = repository;
    }

    public SavingsAccount execute(String iban) throws AccountNotFoundException {
        return this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
    }
}
