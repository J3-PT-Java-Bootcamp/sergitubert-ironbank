package com.ironhack.sergitubertironbank.users.Admin;

import com.ironhack.sergitubertironbank.users.Admin.dto.CreateAdminDto;
import com.ironhack.sergitubertironbank.shared.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Admin extends BaseUser {

    public Admin(String name, String email) {
        super(name, email);
    }


    public static Admin fromDto(CreateAdminDto dto) {
        return new Admin(dto.getName(), dto.getEmail());
    }
}
