package com.ironhack.sergitubertironbank.users.repositories;

import com.ironhack.sergitubertironbank.users.entities.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
