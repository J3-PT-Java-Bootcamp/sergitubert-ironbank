package com.ironhack.sergitubertironbank.accounts.shared.domain;

public class AccountFrozenException extends Exception {
    public AccountFrozenException(String iban) {
        super(String.format("Account %s is frozen", iban));
    }
}
