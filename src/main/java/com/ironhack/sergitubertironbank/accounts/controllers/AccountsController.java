package com.ironhack.sergitubertironbank.accounts.controllers;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.CreditAccountCreator;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.shared.validators.IBAN.IBAN;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final CreditAccountCreator creditAccountCreator;

    public AccountsController(CreditAccountCreator creditAccountCreator) {
        this.creditAccountCreator = creditAccountCreator;
    }

    @PostMapping()
    public ResponseEntity<CreditAccount> createCreditAccount(@RequestBody @Valid CreateCreditAccountDto dto) throws OwnerNotFoundException {
        var creditAccount = this.creditAccountCreator.execute(dto);
        return new ResponseEntity<>(creditAccount, HttpStatus.CREATED);
    }

    @PatchMapping("/{iban}/balance")
    public void modifyBalance(@PathVariable String iban) {

    }

    @GetMapping("/savings/{iban}")
    public ResponseEntity<SavingsAccount> getSavingAccount(@PathVariable @IBAN String iban) {
        return null;
    }

    @GetMapping("/checking-account/{iban}")
    public ResponseEntity<CheckingAccount> getCheckingAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/credit-account/{iban}")
    public ResponseEntity<CreditAccount> getCreditAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/student-checking-account/{iban}")
    public ResponseEntity<StudentCheckingAccount> getStudentCheckingAccount(@PathVariable String iban) {
        return null;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@RequestBody @Valid Object transferDto) {
        //log transactions
        // validate fromAccount is from the user requesting
        // validate toAccount exists
        // validate both accounts are not frozen
        // validate fromAccount enough balance
        return null;
    }
}
