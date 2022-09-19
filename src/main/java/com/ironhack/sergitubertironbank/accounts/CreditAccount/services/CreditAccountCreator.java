package com.ironhack.sergitubertironbank.accounts.CreditAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccountRepository;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import org.springframework.stereotype.Service;

@Service
public final class CreditAccountCreator {
    private final CreditAccountRepository repository;
    private final AccountHolderRepository accountHolderRepository;

    public CreditAccountCreator(CreditAccountRepository repository, AccountHolderRepository ownerRepository) {
        this.repository = repository;
        this.accountHolderRepository = ownerRepository;
    }

    public CreditAccount execute(CreateCreditAccountDto dto) throws OwnerNotFoundException {
        var user = this.accountHolderRepository.findById(dto.getOwnerId()).orElseThrow(() -> new OwnerNotFoundException(dto.getOwnerId()));
        var account = CreditAccount.fromDto(dto, user);
        this.repository.save(account);
        return account;
    }
}
