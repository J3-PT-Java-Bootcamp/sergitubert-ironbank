package com.ironhack.sergitubertironbank.accounts.controllers;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.shared.BaseAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @PostMapping()
    public ResponseEntity<CreditAccount> createCreditAccount() {
        return null;
    }

    @PatchMapping("/{:iban}/balance")
    public void modifyBalance(@PathVariable String iban) {

    }

    @GetMapping("/savings/{:iban}")
    public ResponseEntity<SavingsAccount> getSavingAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/checking-account/{:iban}")
    public ResponseEntity<CheckingAccount> getCheckingAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/credit-account/{:iban}")
    public ResponseEntity<CreditAccount> getCreditAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/student-checking-account/{:iban}")
    public ResponseEntity<StudentCheckingAccount> getStudentCheckingAccount(@PathVariable String iban) {
        return null;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@RequestBody @Valid Object transferDto) {
        //log transactions
        return null;
    }
}
