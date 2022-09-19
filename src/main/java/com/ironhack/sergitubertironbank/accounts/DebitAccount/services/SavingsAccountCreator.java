package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateSavingsAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.SavingsAccountRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import org.springframework.stereotype.Service;

@Service
public final class SavingsAccountCreator {
    private final SavingsAccountRepository repository;
    private final AccountHolderRepository accountHolderRepository;

    public SavingsAccountCreator(SavingsAccountRepository repository, AccountHolderRepository ownerRepository) {
        this.repository = repository;
        this.accountHolderRepository = ownerRepository;
    }

    public SavingsAccount execute(CreateSavingsAccountDto dto) throws OwnerNotFoundException {
        var user = this.accountHolderRepository.findById(dto.getOwnerId()).orElseThrow(() -> new OwnerNotFoundException(dto.getOwnerId()));
        var account = SavingsAccount.fromDto(dto, user);
        this.repository.save(account);
        return account;
    }
}
