package com.ironhack.sergitubertironbank.accounts.shared.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransferDto {
    private String senderIban;
    private String receiverIban;
    private BigDecimal amount;
}
