package com.ironhack.sergitubertironbank.accounts.shared.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseAccountRepository extends JpaRepository<BaseAccount, String> {
}
