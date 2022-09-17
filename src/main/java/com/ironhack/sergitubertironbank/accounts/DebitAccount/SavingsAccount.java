package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class SavingsAccount extends BaseDebitAccount {

    protected static final Double DEFAULT_INTEREST_RATE = 0.0025;
    protected static final Double MAXIMUM_INTEREST_RATE_THRESHOLD = 0.5;
    protected static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal("1000.0"));
    protected static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal("100.0"));


    private Double interestRate;

    @Override
    public Money getMinimumBalanceThreshold() {
        return SavingsAccount.MINIMUM_BALANCE_THRESHOLD;
    }

    // Create checking account

}
