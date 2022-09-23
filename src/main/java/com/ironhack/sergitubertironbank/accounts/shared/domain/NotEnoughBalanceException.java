package com.ironhack.sergitubertironbank.accounts.shared.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughBalanceException extends Exception {
    public NotEnoughBalanceException(String iban) {
        super(String.format("Account with IBAN: %s don't have enough balance", iban));
    }
}
