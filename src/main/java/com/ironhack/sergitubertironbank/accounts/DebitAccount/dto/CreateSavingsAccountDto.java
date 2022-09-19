package com.ironhack.sergitubertironbank.accounts.DebitAccount.dto;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CreateSavingsAccountDto {
    @NotNull
    private Long ownerId;

    private Long secondaryOwnerId;

    @DecimalMin(value = SavingsAccount.MINIMUM_BALANCE_THRESHOLD_RAW)
    private BigDecimal balance;

    @DecimalMax(value = SavingsAccount.MAXIMUM_INTEREST_RATE_THRESHOLD_RAW)
    private BigDecimal interestRate;
}
