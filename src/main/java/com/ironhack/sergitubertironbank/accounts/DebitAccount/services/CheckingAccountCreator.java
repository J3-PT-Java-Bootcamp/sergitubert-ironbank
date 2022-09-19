package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.BaseDebitAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateCheckingAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateSavingsAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateStudentCheckingAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.CheckingAccountRepository;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories.StudentCheckingAccountRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import org.springframework.stereotype.Service;

@Service
public final class CheckingAccountCreator {
    private final CheckingAccountRepository repository;
    private final StudentCheckingAccountCreator studentCheckingAccountCreator;
    private final AccountHolderRepository accountHolderRepository;

    public CheckingAccountCreator(CheckingAccountRepository checkingAccountRepository, StudentCheckingAccountCreator studentCheckingAccountCreator, AccountHolderRepository ownerRepository) {
        this.repository = checkingAccountRepository;
        this.studentCheckingAccountCreator = studentCheckingAccountCreator;
        this.accountHolderRepository = ownerRepository;
    }

    public BaseDebitAccount execute(CreateCheckingAccountDto dto) throws OwnerNotFoundException, UserIsNotStudentException {
        var user = this.accountHolderRepository.findById(dto.getOwnerId()).orElseThrow(() -> new OwnerNotFoundException(dto.getOwnerId()));
        if(user.hasStudentAge()) {
            return this.studentCheckingAccountCreator.execute(CreateStudentCheckingAccountDto.fromDto(dto));
        }
        var account = CheckingAccount.fromDto(dto, user);
        this.repository.save(account);
        return account;
    }
}
