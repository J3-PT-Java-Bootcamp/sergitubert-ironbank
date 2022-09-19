package com.ironhack.sergitubertironbank.accounts.DebitAccount.dto;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CreateCheckingAccountDto {
    @NotNull
    private Long ownerId;

    private Long secondaryOwnerId;

    @DecimalMin(value = CheckingAccount.MINIMUM_BALANCE_THRESHOLD_RAW)
    private BigDecimal balance;

}
