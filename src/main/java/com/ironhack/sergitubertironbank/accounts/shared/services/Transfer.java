package com.ironhack.sergitubertironbank.accounts.shared.services;

import com.ironhack.sergitubertironbank.accounts.shared.domain.AccountFrozenException;
import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.accounts.shared.domain.NotEnoughBalanceException;
import com.ironhack.sergitubertironbank.accounts.shared.dto.TransferDto;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.shared.repositories.BaseUserRepository;
import org.springframework.stereotype.Service;

@Service
public final class Transfer {
    private final BaseAccountRepository accountRepository;
    private final BaseUserRepository userRepository;

    public Transfer(BaseAccountRepository repository, BaseUserRepository userRepository) {
        this.accountRepository = repository;
        this.userRepository = userRepository;
    }

    public void execute(TransferDto dto) throws AccountNotFoundException, AccountFrozenException, NotEnoughBalanceException {
        //log transactions
        // validate fromAccount is from the user requesting
        // validate toAccount exists
        var fromAccount = this.accountRepository.findById(dto.getSenderIban())
                .orElseThrow(() -> new AccountNotFoundException(dto.getSenderIban()));
        var toAccount = this.accountRepository.findById(dto.getReceiverIban())
                .orElseThrow(() -> new AccountNotFoundException(dto.getReceiverIban()));

        fromAccount.transfer(toAccount, new Money(dto.getAmount()));
        // save accounts in one db transaction
    }
}
