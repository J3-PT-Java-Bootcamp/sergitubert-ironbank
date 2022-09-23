package com.ironhack.sergitubertironbank.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String keycloakId;

    @Column(insertable = false, updatable = false)
    private String dtype;

    public BaseUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @JsonIgnore
    public boolean isAdmin() {
        return this.dtype.equals("Admin");
    }
}
