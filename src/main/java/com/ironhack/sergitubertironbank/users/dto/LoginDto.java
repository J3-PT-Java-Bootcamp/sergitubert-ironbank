package com.ironhack.sergitubertironbank.users.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
