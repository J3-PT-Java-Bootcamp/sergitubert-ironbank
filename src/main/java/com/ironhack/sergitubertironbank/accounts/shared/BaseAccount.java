package com.ironhack.sergitubertironbank.accounts.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseAccount {
    @Id
    @Column(name = "accountNumber", nullable = false)
    private Long id;



}
