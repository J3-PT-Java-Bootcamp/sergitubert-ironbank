package com.ironhack.sergitubertironbank.accounts.CreditAccount;

import com.ironhack.sergitubertironbank.accounts.shared.BaseAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditAccount extends BaseAccount {
    private final static Double DEFAULT_CREDIT_LIMIT = 100.0;
    private final static Double MAXIMUM_CREDIT_THRESHOLD = 100_000.0;
    private final static Double DEFAULT_INTEREST_RATE = 0.2;
    private final static Double MINIMUM_INTEREST_RATE_THRESHOLD = 0.1;

    private Double creditLimit;
    private Double interestRate;
}
