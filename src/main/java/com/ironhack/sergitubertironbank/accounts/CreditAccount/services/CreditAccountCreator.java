package com.ironhack.sergitubertironbank.accounts.CreditAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccountRepository;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.shared.OwnerRepository;

public final class CreditAccountCreator {
    private final CreditAccountRepository repository;
    private final OwnerRepository ownerRepository;

    public CreditAccountCreator(CreditAccountRepository repository, OwnerRepository ownerRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
    }

    public CreditAccount execute(CreateCreditAccountDto dto) throws OwnerNotFoundException {
        var user = this.ownerRepository.findById(dto.getOwnerId()).orElseThrow(() -> new OwnerNotFoundException(dto.getOwnerId()));
        var account = CreditAccount.fromDto(dto, user);
        this.repository.save(account);
        return account;
    }
}
