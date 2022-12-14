package com.ironhack.sergitubertironbank.accounts.shared.services;

import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccountRepository;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.accounts.shared.dto.ModifyBalanceDto;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class BalanceModifier {

    private final BaseAccountRepository repository;

    public BalanceModifier(BaseAccountRepository repository) {
        this.repository = repository;
    }

    public BaseAccount execute(String iban, ModifyBalanceDto dto) throws AccountNotFoundException {
        var account = this.repository.findById(iban).orElseThrow(() -> new AccountNotFoundException(iban));
        account.setBalance(new Money(dto.getBalance()));
        this.repository.save(account);
        return account;
    }
}
