package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CheckingAccount extends BaseDebitAccount {
    private static final Money MINIMUM_BALANCE_THRESHOLD = new Money(new BigDecimal("250.0"));
    private static final Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12.0"));

    @Override
    public Money getMinimumBalanceThreshold() {
        return CheckingAccount.MINIMUM_BALANCE_THRESHOLD;
    }
}
