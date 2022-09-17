package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.StudentCheckingAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class StudentCheckingAccountFinder {

    private final StudentCheckingAccountRepository repository;

    public StudentCheckingAccountFinder(StudentCheckingAccountRepository repository) {
        this.repository = repository;
    }

    public StudentCheckingAccount execute(String iban) throws AccountNotFoundException {
        return this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
    }
}
