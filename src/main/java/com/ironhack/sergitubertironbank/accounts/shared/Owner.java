package com.ironhack.sergitubertironbank.accounts.shared;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.shared.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Owner extends BaseUser {

    @OneToMany(mappedBy = "primaryOwner")
    private List<CreditAccount> primaryCreditAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<CreditAccount> secondaryCreditAccounts;

    @OneToMany(mappedBy = "primaryOwner")
    private List<CheckingAccount> primaryCheckingAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<CheckingAccount> secondaryCheckingAccounts;

    @OneToMany(mappedBy = "primaryOwner")
    private List<SavingsAccount> primarySavingsAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<SavingsAccount> secondarySavingsAccounts;

    @OneToMany(mappedBy = "primaryOwner")
    private List<StudentCheckingAccount> primaryStudentCheckingAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<StudentCheckingAccount> secondaryStudentCheckingAccounts;


}
