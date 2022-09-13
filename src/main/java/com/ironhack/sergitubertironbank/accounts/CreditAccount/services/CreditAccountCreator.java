package com.ironhack.sergitubertironbank.accounts.CreditAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccountRepository;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;

public final class CreditAccountCreator {
    private final CreditAccountRepository repository;


    public CreditAccountCreator(CreditAccountRepository repository) {
        this.repository = repository;
    }

    public CreditAccount execute(CreateCreditAccountDto dto) {
        return null;
    }
}
