package com.ironhack.sergitubertironbank.accounts.CreditAccount;

import com.ironhack.sergitubertironbank.accounts.shared.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditAccount extends BaseAccount {
    public final static String MAXIMUM_CREDIT_THRESHOLD_RAW = "100000.0";
    public final static String MINIMUM_INTEREST_RATE_THRESHOLD_RAW = "0.1";

    private final static Money DEFAULT_CREDIT_LIMIT = new Money(new BigDecimal("100.0"));
    private final static Money MAXIMUM_CREDIT_THRESHOLD = new Money(new BigDecimal(CreditAccount.MAXIMUM_CREDIT_THRESHOLD_RAW));
    private final static BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.2");
    private final static BigDecimal MINIMUM_INTEREST_RATE_THRESHOLD = new BigDecimal(CreditAccount.MINIMUM_INTEREST_RATE_THRESHOLD_RAW);

    private Double creditLimit;
    private Double interestRate;

}
