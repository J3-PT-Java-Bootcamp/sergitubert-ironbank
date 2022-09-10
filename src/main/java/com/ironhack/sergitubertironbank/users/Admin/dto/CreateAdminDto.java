package com.ironhack.sergitubertironbank.users.Admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class CreateAdminDto {

    @NotEmpty
    private String name;
}
