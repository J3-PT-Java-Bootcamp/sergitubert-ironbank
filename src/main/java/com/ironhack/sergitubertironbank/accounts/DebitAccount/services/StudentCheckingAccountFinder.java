package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.StudentCheckingAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.exceptions.AccountHolderNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.services.AccountHolderFinder;
import org.springframework.stereotype.Service;

@Service
public final class StudentCheckingAccountFinder {

    private final StudentCheckingAccountRepository repository;
    private final AccountHolderFinder accountHolderFinder;

    public StudentCheckingAccountFinder(StudentCheckingAccountRepository repository, AccountHolderFinder accountHolderFinder) {
        this.repository = repository;
        this.accountHolderFinder = accountHolderFinder;
    }

    public StudentCheckingAccount execute(String iban, String keycloakId) throws AccountNotFoundException, AccountHolderNotFoundException {
        var accountHolder = this.accountHolderFinder.execute(keycloakId);
        var account = this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
        if(!accountHolder.isAdmin() && !account.isOwner(accountHolder)) throw new AccountNotFoundException(iban);
        return account;
    }
}
