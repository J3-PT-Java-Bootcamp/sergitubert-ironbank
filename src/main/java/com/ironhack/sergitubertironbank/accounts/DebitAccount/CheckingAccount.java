package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CheckingAccount extends BaseDebitAccount {
    private static final Double MINIMUM_BALANCE_THRESHOLD = 250.0;
    private static final Double MONTHLY_MAINTENANCE_FEE = 12.0;
}
