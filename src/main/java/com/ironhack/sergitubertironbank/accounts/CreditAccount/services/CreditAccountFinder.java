package com.ironhack.sergitubertironbank.accounts.CreditAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.exceptions.AccountHolderNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.services.AccountHolderFinder;
import org.springframework.stereotype.Service;

@Service
public final class CreditAccountFinder {
    private final CreditAccountRepository repository;
    private final AccountHolderFinder accountHolderFinder;

    public CreditAccountFinder(CreditAccountRepository repository, AccountHolderFinder accountHolderFinder) {
        this.repository = repository;
        this.accountHolderFinder = accountHolderFinder;
    }

    public CreditAccount execute(String iban, String keycloakId) throws AccountNotFoundException, AccountHolderNotFoundException {
        var accountHolder = this.accountHolderFinder.execute(keycloakId);
        var account = this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
        if(!accountHolder.isAdmin() && !account.isOwner(accountHolder)) throw new AccountNotFoundException(iban);
        return account;
    }
}
