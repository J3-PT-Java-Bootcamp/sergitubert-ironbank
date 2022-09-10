package com.ironhack.sergitubertironbank.users.AccountHolder.services;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolderRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.Address;
import com.ironhack.sergitubertironbank.users.AccountHolder.AddressRepository;
import com.ironhack.sergitubertironbank.users.AccountHolder.dto.CreateAddressDto;
import com.ironhack.sergitubertironbank.users.AccountHolder.exceptions.AccountHolderNotFoundException;

public class AddressCreator {
    private final AddressRepository repository;
    private final AccountHolderRepository accountHolderRepository;

    public AddressCreator(AddressRepository repository, AccountHolderRepository accountHolderRepository) {
        this.repository = repository;
        this.accountHolderRepository = accountHolderRepository;
    }

    public Address execute(CreateAddressDto dto) throws AccountHolderNotFoundException {
        var user = this.accountHolderRepository.findById(dto.getUserId()).orElseThrow(() -> new AccountHolderNotFoundException(dto.getUserId()));
        var address = Address.fromDto(dto, user);
        return this.repository.save(address);
    }
}
