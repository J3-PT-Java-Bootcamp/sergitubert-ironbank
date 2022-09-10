package com.ironhack.sergitubertironbank.users.AccountHolder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountHolderNotFoundException extends Exception {
    public AccountHolderNotFoundException(Long id) {
        super(String.format("Account holder with id: %d not found", id));
    }
}
