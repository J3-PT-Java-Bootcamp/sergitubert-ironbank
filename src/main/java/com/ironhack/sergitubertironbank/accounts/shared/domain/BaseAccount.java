package com.ironhack.sergitubertironbank.accounts.shared.domain;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private AccountHolder secondaryOwner;

    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default NOW()")
    @CreatedDate
    private Instant createdAt;

    public BaseAccount(String iban, Money balance, AccountHolder primaryOwner) {
        this.iban = iban;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public void transfer(BaseAccount receiver, Money amount) {

    }
}
