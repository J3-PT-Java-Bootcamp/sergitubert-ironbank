package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateStudentCheckingAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.StudentCheckingAccountRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public final class StudentCheckingAccountCreator {
    private final StudentCheckingAccountRepository repository;
    private final AccountHolderRepository accountHolderRepository;

    public StudentCheckingAccountCreator(StudentCheckingAccountRepository repository, AccountHolderRepository ownerRepository) {
        this.repository = repository;
        this.accountHolderRepository = ownerRepository;
    }

    public StudentCheckingAccount execute(@Valid CreateStudentCheckingAccountDto dto) throws OwnerNotFoundException, UserIsNotStudentException {
        var user = this.accountHolderRepository.findById(dto.getOwnerId()).orElseThrow(() -> new OwnerNotFoundException(dto.getOwnerId()));
        if (!user.hasStudentAge()) throw new UserIsNotStudentException(dto.getOwnerId(), user.getAge());
        var account = StudentCheckingAccount.fromDto(dto, user);
        this.repository.save(account);
        return account;
    }
}
