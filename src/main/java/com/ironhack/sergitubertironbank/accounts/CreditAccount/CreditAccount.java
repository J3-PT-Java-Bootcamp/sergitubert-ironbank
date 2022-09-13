package com.ironhack.sergitubertironbank.accounts.CreditAccount;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.shared.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.Money;
import com.ironhack.sergitubertironbank.accounts.shared.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.IbanUtil;

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
    private final static BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.2");
    private final static BigDecimal MINIMUM_INTEREST_RATE_THRESHOLD = new BigDecimal(CreditAccount.MINIMUM_INTEREST_RATE_THRESHOLD_RAW);

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_currency"))
    })
    @Embedded
    private Money creditLimit;

    private BigDecimal interestRate;

    private CreditAccount(String iban, Money balance, Owner primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(iban, balance, primaryOwner);
    }
    private CreditAccount(String iban, Money balance, Owner primaryOwner, BigDecimal interestRate) {
        super(iban, balance, primaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = CreditAccount.DEFAULT_CREDIT_LIMIT;
    }
    private CreditAccount(String iban, Money balance, Owner primaryOwner, Money creditLimit) {
        super(iban, balance, primaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = CreditAccount.DEFAULT_INTEREST_RATE;
    }

    private CreditAccount(String iban, Money balance, Owner primaryOwner) {
        super(iban, balance, primaryOwner);
        this.creditLimit = CreditAccount.DEFAULT_CREDIT_LIMIT;
        this.interestRate = CreditAccount.DEFAULT_INTEREST_RATE;
    }

    public static CreditAccount create(Money balance, Owner primaryOwner) {
        Iban iban = Iban.random(CountryCode.US);
        return new CreditAccount(iban.toString(), balance, primaryOwner);
    }

    public static CreditAccount create(Money balance, Owner primaryOwner, Money creditLimit) {
        Iban iban = Iban.random(CountryCode.US);
        return new CreditAccount(iban.toString(), balance, primaryOwner, creditLimit);
    }

    public static CreditAccount create(Money balance, Owner primaryOwner, BigDecimal interestRate) {
        Iban iban = Iban.random(CountryCode.US);
        return new CreditAccount(iban.toString(), balance, primaryOwner, interestRate);
    }

    public static CreditAccount create(Money balance, Owner primaryOwner, Money creditLimit, BigDecimal interestRate) {
        Iban iban = Iban.random(CountryCode.US);
        return new CreditAccount(iban.toString(), balance, primaryOwner, creditLimit, interestRate);
    }

    public static CreditAccount fromDto(CreateCreditAccountDto dto, Owner primaryOwner) {
        // TODO refactor with ternary?
        var balance = new Money(dto.getBalance());
        if (dto.getInterestRate() != null && dto.getCreditLimit() != null) {
            return CreditAccount.create(balance, primaryOwner, new Money(dto.getCreditLimit()), dto.getInterestRate());
        }
        if (dto.getInterestRate() == null && dto.getCreditLimit() != null) {
            return CreditAccount.create(balance, primaryOwner, new Money(dto.getCreditLimit()));
        }

        if (dto.getInterestRate() != null && dto.getCreditLimit() == null) {
            return CreditAccount.create(balance, primaryOwner, dto.getInterestRate());
        }
        return CreditAccount.create(balance, primaryOwner);
    }

}
