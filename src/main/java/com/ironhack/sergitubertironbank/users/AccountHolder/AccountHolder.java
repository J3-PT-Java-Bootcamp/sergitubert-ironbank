package com.ironhack.sergitubertironbank.users.AccountHolder;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.shared.BaseUser;
import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAccountHolderDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
public class AccountHolder extends BaseUser {
    public static final Integer MAXIMUM_STUDENT_AGE_THRESHOLD = 24;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

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

    protected AccountHolder() {
        super();
    }

    public AccountHolder(String name, LocalDate dateOfBirth, String email) {
        super(name, email);
        this.dateOfBirth = dateOfBirth;
    }

    public boolean hasStudentAge() {
        return this.getAge() <= AccountHolder.MAXIMUM_STUDENT_AGE_THRESHOLD;
    }

    public Integer getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.dateOfBirth, currentDate).getYears();
    }

    public static AccountHolder fromDto(CreateAccountHolderDto dto) {
        return new AccountHolder(dto.getName(), dto.getDateOfBirth(), dto.getEmail());
    }
}
