package com.ironhack.sergitubertironbank.accounts.shared.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ModifyBalanceDto {

    @NotNull
    private BigDecimal balance;

}
