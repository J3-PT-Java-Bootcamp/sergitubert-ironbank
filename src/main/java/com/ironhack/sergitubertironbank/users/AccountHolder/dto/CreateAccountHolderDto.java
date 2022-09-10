package com.ironhack.sergitubertironbank.users.AccountHolder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountHolderDto {
    @NotEmpty
    private String name;

    @NotEmpty
    @Past
    private LocalDate dateOfBirth;

    @Email
    private String email;

    @NotEmpty
    private String password;
}
