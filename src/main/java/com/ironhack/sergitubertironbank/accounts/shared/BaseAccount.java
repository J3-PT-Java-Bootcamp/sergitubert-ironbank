package com.ironhack.sergitubertironbank.accounts.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
public abstract class BaseAccount {

    protected static final Integer PENALTY_FEE = 40;

    @Id
    @Column(name = "iban", nullable = false)
    private String iban;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    @Embedded
    private Money balance;


    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Owner primaryOwner;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Owner secondaryOwner;
}
