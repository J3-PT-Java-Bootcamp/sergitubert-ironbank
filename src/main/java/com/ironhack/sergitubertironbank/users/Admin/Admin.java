package com.ironhack.sergitubertironbank.users.Admin;

import com.ironhack.sergitubertironbank.users.Admin.dto.CreateAdminDto;
import com.ironhack.sergitubertironbank.users.shared.BaseUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Admin extends BaseUser {

    protected Admin() {
        super();
    }

    public Admin(String name) {
        super(name);
    }


    public static Admin fromDto(CreateAdminDto dto) {
        return new Admin(dto.getName());
    }
}
