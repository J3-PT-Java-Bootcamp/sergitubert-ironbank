package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.shared.BaseAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
public class BaseDebitAccount extends BaseAccount {
    private String secretKey;

    @CreatedDate
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;
}
