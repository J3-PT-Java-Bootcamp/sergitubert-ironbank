package com.ironhack.sergitubertironbank.users.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseUser {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

}
