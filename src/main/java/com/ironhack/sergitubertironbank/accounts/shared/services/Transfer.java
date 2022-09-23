package com.ironhack.sergitubertironbank.accounts.shared.services;

import com.ironhack.sergitubertironbank.accounts.shared.domain.AccountFrozenException;
import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.accounts.shared.domain.NotEnoughBalanceException;
import com.ironhack.sergitubertironbank.accounts.shared.dto.TransferDto;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.exceptions.AccountHolderNotFoundException;
import com.ironhack.sergitubertironbank.users.AccountHolder.services.AccountHolderFinder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public final class Transfer {
    private final BaseAccountRepository accountRepository;
    private final AccountHolderFinder accountHolderFinder;

    private final TransactionTemplate transaction;


    public Transfer(BaseAccountRepository repository, AccountHolderFinder accountHolderFinder, TransactionTemplate transaction) {
        this.accountRepository = repository;
        this.accountHolderFinder = accountHolderFinder;
        this.transaction = transaction;
    }

    public void execute(TransferDto dto, String keycloakId) throws AccountNotFoundException, AccountFrozenException, NotEnoughBalanceException, AccountHolderNotFoundException {
        var requester = this.accountHolderFinder.execute(keycloakId);
        var fromAccount = this.accountRepository.findById(dto.getSenderIban())
                .orElseThrow(() -> new AccountNotFoundException(dto.getSenderIban()));
        if (!requester.isAdmin() && !fromAccount.isOwner(requester))
            throw new AccountNotFoundException(dto.getSenderIban());
        var toAccount = this.accountRepository.findById(dto.getReceiverIban())
                .orElseThrow(() -> new AccountNotFoundException(dto.getReceiverIban()));

        fromAccount.transfer(toAccount, new Money(dto.getAmount()));
        transaction.execute(status -> {
            this.accountRepository.saveAll(List.of(fromAccount, toAccount));
            return status;
        });
        //log transactions
    }
}
