package com.ironhack.sergitubertironbank.users.Admin.services;

import com.ironhack.sergitubertironbank.users.Admin.Admin;
import com.ironhack.sergitubertironbank.users.Admin.AdminRepository;
import com.ironhack.sergitubertironbank.users.Admin.dto.CreateAdminDto;
import com.ironhack.sergitubertironbank.users.keycloak.CreateKeycloakUserDto;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakAdminClientService;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakGroups;
import com.ironhack.sergitubertironbank.users.keycloak.KeycloakUserNotCreatedException;
import com.ironhack.sergitubertironbank.users.shared.BaseUserRepository;
import org.springframework.stereotype.Service;

@Service
public final class AdminCreator {
    private final AdminRepository repository;
    private final BaseUserRepository userRepository;
    private final KeycloakAdminClientService keycloak;

    public AdminCreator(AdminRepository repository, BaseUserRepository userRepository, KeycloakAdminClientService keycloak) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.keycloak = keycloak;
    }

    public Admin execute(CreateAdminDto dto) throws KeycloakUserNotCreatedException, UserEmailAlreadyExists {
        var keycloakId = this.keycloak.createKeycloakUser(new CreateKeycloakUserDto(dto.getPassword(), dto.getEmail(), KeycloakGroups.ADMIN));
        var user = this.userRepository.findBaseUserByEmail(dto.getEmail());
        if (user.isPresent()) {
            throw new UserEmailAlreadyExists(dto.getEmail());
        }
        var admin = Admin.fromDto(dto);
        admin.setKeycloakId(keycloakId);
        return this.repository.save(admin);
    }
}
