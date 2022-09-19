package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class StudentCheckingAccount extends BaseDebitAccount {
    private static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal("0.0"));

    @Override
    public Money getMinimumBalanceThreshold() {
        return StudentCheckingAccount.MINIMUM_BALANCE_THRESHOLD;
    }
}