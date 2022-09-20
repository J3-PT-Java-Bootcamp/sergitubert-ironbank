package com.ironhack.sergitubertironbank.users.controllers;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;
import com.ironhack.sergitubertironbank.users.AccountHolder.services.AccountHolderCreator;
import com.ironhack.sergitubertironbank.users.Admin.Admin;
import com.ironhack.sergitubertironbank.users.Admin.dto.CreateAdminDto;
import com.ironhack.sergitubertironbank.users.Admin.services.AdminCreator;
import com.ironhack.sergitubertironbank.users.Admin.services.UserEmailAlreadyExists;
import com.ironhack.sergitubertironbank.users.dto.LoginDto;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakAdminClientService;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakUserNotCreatedException;
import com.ironhack.sergitubertironbank.users.keycloak.LoginFailedException;
import org.keycloak.representations.AccessTokenResponse;
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

    private final KeycloakAdminClientService kc;

    public UsersController(AdminCreator adminCreator, AccountHolderCreator accountHolderCreator, KeycloakAdminClientService kc) {
        this.adminCreator = adminCreator;
        this.accountHolderCreator = accountHolderCreator;
        this.kc = kc;
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

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@Valid @RequestBody LoginDto dto) throws LoginFailedException {
        return new ResponseEntity<>(kc.login(dto.getUsername(), dto.getPassword()), HttpStatus.CREATED);
    }

}
