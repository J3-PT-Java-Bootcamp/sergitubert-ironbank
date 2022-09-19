package com.ironhack.sergitubertironbank.accounts.CreditAccount;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class CreditAccount extends BaseAccount {
    public final static String MAXIMUM_CREDIT_THRESHOLD_RAW = "100000.0";
    public final static String MINIMUM_INTEREST_RATE_THRESHOLD_RAW = "0.1";

    private final static Money DEFAULT_CREDIT_LIMIT = new Money(new BigDecimal("100.0"));
    private final static Money MAXIMUM_CREDIT_THRESHOLD = new Money(new BigDecimal(CreditAccount.MAXIMUM_CREDIT_THRESHOLD_RAW));
    private static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal("0.0"));

    private final static BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.2");
    private final static BigDecimal MINIMUM_INTEREST_RATE_THRESHOLD = new BigDecimal(CreditAccount.MINIMUM_INTEREST_RATE_THRESHOLD_RAW);

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_currency"))
    })
    @Embedded
    private Money creditLimit;

    private BigDecimal interestRate;

    private CreditAccount(String iban, Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(iban, balance, primaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public static CreditAccount create(Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        Iban iban = Iban.random(CountryCode.ES);
        return new CreditAccount(iban.toString(), balance, primaryOwner, creditLimit, interestRate);
    }

    public static CreditAccount fromDto(CreateCreditAccountDto dto, AccountHolder primaryOwner) {
        var balance = new Money(dto.getBalance());
        var creditLimit = dto.getCreditLimit() != null ? new Money(dto.getCreditLimit()) : CreditAccount.DEFAULT_CREDIT_LIMIT;
        var interestRate = dto.getInterestRate() != null ? dto.getInterestRate() : CreditAccount.DEFAULT_INTEREST_RATE;
        return CreditAccount.create(balance, primaryOwner, creditLimit, interestRate);
    }

    @Override
    public boolean isFrozen() {
        return false;
    }

    @Override
    public Money getMinimumBalanceThreshold() {
        return CreditAccount.MINIMUM_BALANCE_THRESHOLD;
    }
}
