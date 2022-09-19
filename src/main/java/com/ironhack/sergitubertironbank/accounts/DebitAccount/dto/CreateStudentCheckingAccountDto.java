package com.ironhack.sergitubertironbank.accounts.DebitAccount.dto;

import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreateStudentCheckingAccountDto {
    @NotNull
    private Long ownerId;

    private Long secondaryOwnerId;

    @DecimalMin(value = StudentCheckingAccount.MINIMUM_BALANCE_THRESHOLD_RAW)
    private BigDecimal balance;

    public static CreateStudentCheckingAccountDto fromDto(CreateCheckingAccountDto dto) {
        return new CreateStudentCheckingAccountDto(dto.getOwnerId(), dto.getSecondaryOwnerId(), dto.getBalance());
    }
}
