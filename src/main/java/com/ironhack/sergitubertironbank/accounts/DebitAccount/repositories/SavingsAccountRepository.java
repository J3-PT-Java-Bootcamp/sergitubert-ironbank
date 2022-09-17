package com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, String> {
}
