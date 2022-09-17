package com.ironhack.sergitubertironbank.accounts.shared.dto;

import java.math.BigDecimal;

public class TransferDto {
    private String senderIban;
    private String receiverIban;
    private BigDecimal amount;
}
