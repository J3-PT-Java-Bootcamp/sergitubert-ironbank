package com.ironhack.sergitubertironbank.accounts.CreditAccount.dto;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Currency;

public class CreateCreditAccountDto {

    @NotEmpty
    private Long ownerId;

    private Long secondaryOwnerId;

    @NotEmpty
    private BigDecimal balance;

    @NotEmpty
    @DecimalMax(value = CreditAccount.MAXIMUM_CREDIT_THRESHOLD_RAW)
    private BigDecimal creditLimit;

    @NotEmpty
    private Currency currency;

    @DecimalMin(value = CreditAccount.MINIMUM_INTEREST_RATE_THRESHOLD_RAW)
    private Double interestRate;
}
