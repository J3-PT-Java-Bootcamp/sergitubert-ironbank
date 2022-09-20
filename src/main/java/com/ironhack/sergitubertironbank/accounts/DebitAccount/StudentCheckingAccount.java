package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateStudentCheckingAccountDto;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class StudentCheckingAccount extends BaseDebitAccount {
    public static final String MINIMUM_BALANCE_THRESHOLD_RAW = "0.0";
    private static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal(StudentCheckingAccount.MINIMUM_BALANCE_THRESHOLD_RAW));

    public static StudentCheckingAccount fromDto(CreateStudentCheckingAccountDto dto, AccountHolder user) {
        var balance = dto.getBalance() != null ? new Money(dto.getBalance()) : StudentCheckingAccount.MINIMUM_BALANCE_THRESHOLD;
        return StudentCheckingAccount.create(balance, user);
    }

    private StudentCheckingAccount(String iban, Money balance, AccountHolder primaryOwner, Status status) {
        super(iban, balance, primaryOwner, status);
    }

    public static StudentCheckingAccount create(Money balance, AccountHolder primaryOwner) {
        Iban iban = Iban.random(CountryCode.ES);
        return new StudentCheckingAccount(iban.toString(), balance, primaryOwner, Status.ACTIVE);
    }

    @Override
    public Money getMinimumBalanceThreshold() {
        return StudentCheckingAccount.MINIMUM_BALANCE_THRESHOLD;
    }
}