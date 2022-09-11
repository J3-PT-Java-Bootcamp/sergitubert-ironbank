package com.ironhack.sergitubertironbank.users.AccountHolder.services;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;
import com.ironhack.sergitubertironbank.users.Admin.services.UserEmailAlreadyExists;
import com.ironhack.sergitubertironbank.users.keycloak.CreateKeycloakUserDto;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakAdminClientService;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakGroups;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakUserNotCreatedException;
import com.ironhack.sergitubertironbank.users.shared.BaseUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderCreator {
    private final AccountHolderRepository repository;
    private final BaseUserRepository userRepository;
    private final KeycloakAdminClientService keycloak;


    public AccountHolderCreator(AccountHolderRepository repository, BaseUserRepository userRepository, KeycloakAdminClientService keycloak) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.keycloak = keycloak;
    }

    public AccountHolder execute(CreateAccountHolderDto dto) throws UserEmailAlreadyExists, KeycloakUserNotCreatedException {
        var keycloakId = this.keycloak.createKeycloakUser(new CreateKeycloakUserDto(dto.getPassword(), dto.getEmail(), KeycloakGroups.ACCOUNT_HOLDER));
        var user = this.userRepository.findBaseUserByEmail(dto.getEmail());
        if (user.isPresent()) {
            throw new UserEmailAlreadyExists(dto.getEmail());
        }
        var holder = AccountHolder.fromDto(dto);
        holder.setKeycloakId(keycloakId);
        return this.repository.save(holder);
    }
}
