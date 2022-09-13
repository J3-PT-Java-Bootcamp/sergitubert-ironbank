package com.ironhack.sergitubertironbank.accounts.CreditAccount.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends Exception {
    public OwnerNotFoundException(Long id) {
        super(String.format("Owner with id: %d not found", id));
    }
}
