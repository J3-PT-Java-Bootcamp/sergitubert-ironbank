package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class SavingsAccount extends BaseDebitAccount{

    protected static final Double DEFAULT_INTEREST_RATE = 0.0025;
    protected static final Double MAXIMUM_INTEREST_RATE_THRESHOLD = 0.5;
    protected static final Double DEFAULT_MINIMUM_BALANCE = 1000.0;
    protected static final Double MINIMUM_BALANCE_THRESHOLD = 100.0;


    private Double interestRate;

    // Create checking account

}
