package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateCheckingAccountDto;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class CheckingAccount extends BaseDebitAccount {
    public static final String MINIMUM_BALANCE_THRESHOLD_RAW = "250.0";
    private static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal(CheckingAccount.MINIMUM_BALANCE_THRESHOLD_RAW));
    private static final Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12.0"));

    public static CheckingAccount fromDto(CreateCheckingAccountDto dto, AccountHolder user, AccountHolder secondaryOwner) {
        var balance = dto.getBalance() != null ? new Money(dto.getBalance()) : CheckingAccount.MINIMUM_BALANCE_THRESHOLD;
        return CheckingAccount.create(balance, user, secondaryOwner);
    }

    private CheckingAccount(String iban, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Status status) {
        super(iban, balance, primaryOwner, secondaryOwner, status);
    }

    public static CheckingAccount create(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        Iban iban = Iban.random(CountryCode.ES);
        return new CheckingAccount(iban.toString(), balance, primaryOwner, secondaryOwner, Status.ACTIVE);
    }

    @Override
    public Money getMinimumBalanceThreshold() {
        return CheckingAccount.MINIMUM_BALANCE_THRESHOLD;
    }
}
