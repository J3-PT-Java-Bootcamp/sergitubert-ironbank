package com.ironhack.sergitubertironbank.users.repositories;

import com.ironhack.sergitubertironbank.users.entities.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
