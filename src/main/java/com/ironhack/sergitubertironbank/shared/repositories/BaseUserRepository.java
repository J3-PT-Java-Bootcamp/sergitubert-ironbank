package com.ironhack.sergitubertironbank.shared.repositories;

import com.ironhack.sergitubertironbank.shared.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
    Optional<BaseUser> findBaseUserByEmail(String email);
}
