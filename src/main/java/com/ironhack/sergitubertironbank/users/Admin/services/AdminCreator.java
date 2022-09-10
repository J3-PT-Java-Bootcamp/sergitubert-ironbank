package com.ironhack.sergitubertironbank.users.Admin.services;

import com.ironhack.sergitubertironbank.users.Admin.Admin;
import com.ironhack.sergitubertironbank.users.Admin.AdminRepository;
import com.ironhack.sergitubertironbank.users.Admin.dto.CreateAdminDto;
import org.springframework.stereotype.Service;

@Service
public final class AdminCreator {
    private final AdminRepository repository;

    public AdminCreator(AdminRepository repository) {
        this.repository = repository;
    }

    public Admin execute(CreateAdminDto dto) {
        // TODO: Create user in keycloak
        var admin = Admin.fromDto(dto);
        return this.repository.save(admin);
    }
}
