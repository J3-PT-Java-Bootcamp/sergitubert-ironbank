package com.ironhack.sergitubertironbank.accounts.controllers;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.CreditAccountCreator;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.shared.AccountNotFoundException;
import com.ironhack.sergitubertironbank.accounts.shared.BalanceModifier;
import com.ironhack.sergitubertironbank.accounts.shared.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.ModifyBalanceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final CreditAccountCreator creditAccountCreator;
    private final BalanceModifier balanceModifier;

    public AccountsController(CreditAccountCreator creditAccountCreator, BalanceModifier balanceModifier) {
        this.creditAccountCreator = creditAccountCreator;
        this.balanceModifier = balanceModifier;
    }

    @PostMapping("/credit")
    public ResponseEntity<CreditAccount> createCreditAccount(@RequestBody @Valid CreateCreditAccountDto dto) throws OwnerNotFoundException {
        var creditAccount = this.creditAccountCreator.execute(dto);
        return new ResponseEntity<>(creditAccount, HttpStatus.CREATED);
    }

    @PatchMapping("/{iban}/balance")
    public ResponseEntity<BaseAccount> modifyBalance(@PathVariable String iban, @RequestBody @Valid ModifyBalanceDto dto) throws AccountNotFoundException {
        var account = this.balanceModifier.execute(iban, dto);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/savings/{iban}")
    public ResponseEntity<SavingsAccount> getSavingAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/checking/{iban}")
    public ResponseEntity<CheckingAccount> getCheckingAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/credit/{iban}")
    public ResponseEntity<CreditAccount> getCreditAccount(@PathVariable String iban) {
        return null;
    }

    @GetMapping("/student-checking/{iban}")
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
