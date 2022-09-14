package com.ironhack.sergitubertironbank.accounts.CreditAccount.dto;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CreateCreditAccountDto {

    @NotNull
    private Long ownerId;

    private Long secondaryOwnerId;

    @NotNull
    private BigDecimal balance;

    @DecimalMax(value = CreditAccount.MAXIMUM_CREDIT_THRESHOLD_RAW)
    private BigDecimal creditLimit;

    @DecimalMin(value = CreditAccount.MINIMUM_INTEREST_RATE_THRESHOLD_RAW)
    private BigDecimal interestRate;
}
