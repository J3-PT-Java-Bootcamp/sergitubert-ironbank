package com.ironhack.sergitubertironbank.accounts.shared.domain;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseAccount {

    protected static final Money PENALTY_FEE = new Money(new BigDecimal("40.0"));

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

    public abstract boolean isFrozen();

    public abstract Money getMinimumBalanceThreshold();

    public void transfer(BaseAccount receiver, Money amount) throws AccountFrozenException, NotEnoughBalanceException {
        if (this.isFrozen()) throw new AccountFrozenException(this.getIban());
        if (receiver.isFrozen()) throw new AccountFrozenException(receiver.getIban());

        if (this.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
            throw new NotEnoughBalanceException(this.getIban());
        }

        this.getBalance().decreaseAmount(amount.getAmount());
        receiver.getBalance().increaseAmount(amount.getAmount());
        if (this.getBalance().getAmount().compareTo(this.getMinimumBalanceThreshold().getAmount()) < 0) {
            this.getBalance().decreaseAmount(BaseAccount.PENALTY_FEE);
        }

    }
}
