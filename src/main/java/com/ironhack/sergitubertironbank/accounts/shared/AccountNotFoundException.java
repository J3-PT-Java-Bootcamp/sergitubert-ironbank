package com.ironhack.sergitubertironbank.accounts.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String iban) {
        super(String.format("No account found with IBAN: %s", iban));
    }
}
