package com.ironhack.sergitubertironbank.accounts.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
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


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Owner primaryOwner;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Owner secondaryOwner;

    @CreatedDate
    private LocalDate createdAt;

    public BaseAccount(String iban, Money balance, Owner primaryOwner) {
        this.iban = iban;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }
}
