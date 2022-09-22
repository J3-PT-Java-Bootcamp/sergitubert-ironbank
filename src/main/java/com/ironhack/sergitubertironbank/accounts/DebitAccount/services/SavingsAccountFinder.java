package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.SavingsAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.exceptions.AccountHolderNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.services.AccountHolderFinder;
import org.springframework.stereotype.Service;

@Service
public final class SavingsAccountFinder {

    private final SavingsAccountRepository repository;
    private final AccountHolderFinder accountHolderFinder;

    public SavingsAccountFinder(SavingsAccountRepository repository, AccountHolderFinder accountHolderFinder) {
        this.repository = repository;
        this.accountHolderFinder = accountHolderFinder;
    }

    public SavingsAccount execute(String iban, String keycloakId) throws AccountNotFoundException, AccountHolderNotFoundException {
        var accountHolder = this.accountHolderFinder.execute(keycloakId);
        var account = this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
        if(!accountHolder.isAdmin() && !account.isOwner(accountHolder)) throw new AccountNotFoundException(iban);
        return account;
    }
}
