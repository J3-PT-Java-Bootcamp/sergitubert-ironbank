package com.ironhack.sergitubertironbank.accounts.DebitAccount.repositories;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingAccountRepository extends JpaRepository<StudentCheckingAccount, String> {
}
