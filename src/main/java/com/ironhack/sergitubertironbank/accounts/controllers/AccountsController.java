package com.ironhack.sergitubertironbank.accounts.controllers;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.CreditAccountCreator;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.CreditAccountFinder;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.services.CheckingAccountFinder;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.services.SavingsAccountFinder;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.services.StudentCheckingAccountFinder;
import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.dto.ModifyBalanceDto;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.accounts.shared.services.BalanceModifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final CreditAccountCreator creditAccountCreator;
    private final BalanceModifier balanceModifier;
    private final SavingsAccountFinder savingsAccountFinder;
    private final CheckingAccountFinder checkingAccountFinder;
    private final StudentCheckingAccountFinder studentCheckingAccountFinder;
    private final CreditAccountFinder creditAccountFinder;


    public AccountsController(CreditAccountCreator creditAccountCreator, BalanceModifier balanceModifier, SavingsAccountFinder savingsAccountFinder, CheckingAccountFinder checkingAccountFinder, StudentCheckingAccountFinder studentCheckingAccountFinder, CreditAccountFinder creditAccountFinder) {
        this.creditAccountCreator = creditAccountCreator;
        this.balanceModifier = balanceModifier;
        this.savingsAccountFinder = savingsAccountFinder;
        this.checkingAccountFinder = checkingAccountFinder;
        this.studentCheckingAccountFinder = studentCheckingAccountFinder;
        this.creditAccountFinder = creditAccountFinder;
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
    public ResponseEntity<SavingsAccount> getSavingAccount(@PathVariable String iban) throws AccountNotFoundException {
        var account = this.savingsAccountFinder.execute(iban);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/checking/{iban}")
    public ResponseEntity<CheckingAccount> getCheckingAccount(@PathVariable String iban) throws AccountNotFoundException {
        var account = this.checkingAccountFinder.execute(iban);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/credit/{iban}")
    public ResponseEntity<CreditAccount> getCreditAccount(@PathVariable String iban) throws AccountNotFoundException {
        var account = this.creditAccountFinder.execute(iban);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/student-checking/{iban}")
    public ResponseEntity<StudentCheckingAccount> getStudentCheckingAccount(@PathVariable String iban) throws AccountNotFoundException {
        var account = this.studentCheckingAccountFinder.execute(iban);
        return new ResponseEntity<>(account, HttpStatus.OK);
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
