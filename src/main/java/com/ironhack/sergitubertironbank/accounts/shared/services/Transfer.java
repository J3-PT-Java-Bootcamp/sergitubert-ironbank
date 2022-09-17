package com.ironhack.sergitubertironbank.accounts.shared.services;

import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.dto.TransferDto;
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

    public void execute(TransferDto dto) {
        //log transactions
        // validate fromAccount is from the user requesting
        // validate toAccount exists
        // validate both accounts are not frozen
        // validate fromAccount enough balance
    }
}
