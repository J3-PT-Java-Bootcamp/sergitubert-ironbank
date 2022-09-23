package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateSavingsAccountDto;
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
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SavingsAccount extends BaseDebitAccount {

    public static final String MAXIMUM_INTEREST_RATE_THRESHOLD_RAW = "0.5";
    public static final String MINIMUM_BALANCE_THRESHOLD_RAW = "100.0";

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");
    private static final BigDecimal MAXIMUM_INTEREST_RATE_THRESHOLD = new BigDecimal(SavingsAccount.MAXIMUM_INTEREST_RATE_THRESHOLD_RAW);
    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal("1000.0"));
    private static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal(SavingsAccount.MINIMUM_BALANCE_THRESHOLD_RAW));


    private BigDecimal interestRate;

    private SavingsAccount(String iban, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, Status status) {
        super(iban, balance, primaryOwner, secondaryOwner, status);
        this.interestRate = interestRate;
    }

    public static SavingsAccount create(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        Iban iban = Iban.random(CountryCode.ES);
        return new SavingsAccount(iban.toString(), balance, primaryOwner, secondaryOwner, interestRate, Status.ACTIVE);
    }

    public static SavingsAccount fromDto(CreateSavingsAccountDto dto, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        var balance = dto.getBalance() != null ? new Money(dto.getBalance()) : SavingsAccount.DEFAULT_MINIMUM_BALANCE;
        var interestRate = dto.getInterestRate() != null ? dto.getInterestRate() : SavingsAccount.DEFAULT_INTEREST_RATE;
        return SavingsAccount.create(balance, primaryOwner, secondaryOwner, interestRate);
    }

    @Override
    public Money getMinimumBalanceThreshold() {
        return SavingsAccount.MINIMUM_BALANCE_THRESHOLD;
    }

}
