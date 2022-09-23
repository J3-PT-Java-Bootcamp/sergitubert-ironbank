package com.ironhack.sergitubertironbank.accounts.DebitAccount;

import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.domain.Money;
import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseDebitAccount extends BaseAccount {
    private String secretKey;

    @Enumerated(EnumType.STRING)
    private Status status;

    public BaseDebitAccount(String iban, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Status status) {
        super(iban, balance, primaryOwner, secondaryOwner);
        this.status = status;
    }

    @Override
    public boolean isFrozen() {
        return this.status == Status.FROZEN;
    }


}
