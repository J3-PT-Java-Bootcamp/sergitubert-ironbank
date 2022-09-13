package com.ironhack.sergitubertironbank.users.AccountHolder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountHolderDto {
    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateOfBirth;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;
}
