package com.ironhack.sergitubertironbank.users.repositories;

import com.ironhack.sergitubertironbank.users.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
