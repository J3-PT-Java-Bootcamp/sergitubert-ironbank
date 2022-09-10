package com.ironhack.sergitubertironbank.users.Admin.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserEmailAlreadyExists extends Exception {
    public UserEmailAlreadyExists(String email) {
        super(String.format("User with email: %s already exists", email));
    }
}
