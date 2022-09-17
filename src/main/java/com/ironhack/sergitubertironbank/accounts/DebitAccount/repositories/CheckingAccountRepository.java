package com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, String> {
}
