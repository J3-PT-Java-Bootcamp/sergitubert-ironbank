package com.ironhack.sergitubertironbank.accounts.shared;

import com.ironhack.sergitubertironbank.shared.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Owner extends BaseUser {
}
