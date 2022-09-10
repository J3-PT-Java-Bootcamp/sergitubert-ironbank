package com.ironhack.sergitubertironbank.users.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseUser {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    public BaseUser(String name) {
        this.name = name;
    }
}
