package com.ironhack.sergitubertironbank.users.controllers;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;
import com.ironhack.sergitubertironbank.users.AccountHolder.services.AccountHolderCreator;
import com.ironhack.sergitubertironbank.users.Admin.Admin;
import com.ironhack.sergitubertironbank.users.Admin.dto.CreateAdminDto;
import com.ironhack.sergitubertironbank.users.Admin.services.AdminCreator;
import com.ironhack.sergitubertironbank.users.Admin.services.UserEmailAlreadyExists;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakUserNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public final class UsersController {
    private final AdminCreator adminCreator;
    private final AccountHolderCreator accountHolderCreator;

    public UsersController(AdminCreator adminCreator, AccountHolderCreator accountHolderCreator) {
        this.adminCreator = adminCreator;
        this.accountHolderCreator = accountHolderCreator;
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> createAdmin(@RequestBody @Valid CreateAdminDto dto) throws KeycloakUserNotCreatedException, UserEmailAlreadyExists {
        var admin = this.adminCreator.execute(dto);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PostMapping("/account-holder")
    public ResponseEntity<AccountHolder> createAccountHolder(@RequestBody @Valid CreateAccountHolderDto dto) throws KeycloakUserNotCreatedException, UserEmailAlreadyExists {
        var accountHolder = this.accountHolderCreator.execute(dto);
        return new ResponseEntity<>(accountHolder, HttpStatus.CREATED);
    }


}
