package com.ironhack.sergitubertironbank.accounts.shared.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountFrozenException extends Exception {
    public AccountFrozenException(String iban) {
        super(String.format("Account %s is frozen", iban));
    }
}
