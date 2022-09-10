package com.ironhack.sergitubertironbank.users.keycloak;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateKeycloakUserDto {
    private String password;
    private String email;
    private KeycloakGroups group;
}
