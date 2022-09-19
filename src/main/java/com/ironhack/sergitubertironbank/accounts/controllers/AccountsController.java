package com.ironhack.sergitubertironbank.accounts.controllers;

import com.ironhack.sergitubertironbank.accounts.CreditAccount.CreditAccount;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.dto.CreateCreditAccountDto;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.CreditAccountCreator;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.CreditAccountFinder;
import com.ironhack.sergitubertironbank.accounts.CreditAccount.services.OwnerNotFoundException;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.BaseDebitAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.CheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.SavingsAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.StudentCheckingAccount;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateCheckingAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.dto.CreateSavingsAccountDto;
import com.ironhack.sergitubertironbank.accounts.DebitAccount.services.*;
import com.ironhack.sergitubertironbank.accounts.shared.domain.AccountFrozenException;
import com.ironhack.sergitubertironbank.accounts.shared.domain.BaseAccount;
import com.ironhack.sergitubertironbank.accounts.shared.domain.NotEnoughBalanceException;
import com.ironhack.sergitubertironbank.accounts.shared.dto.ModifyBalanceDto;
import com.ironhack.sergitubertironbank.accounts.shared.dto.TransferDto;
import com.ironhack.sergitubertironbank.accounts.shared.exceptions.AccountNotFoundException;
import com.ironhack.sergitubertironbank.accounts.shared.services.BalanceModifier;
import com.ironhack.sergitubertironbank.accounts.shared.services.Transfer;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final HttpServletRequest request;

    private final CreditAccountCreator creditAccountCreator;
    private final BalanceModifier balanceModifier;
    private final SavingsAccountFinder savingsAccountFinder;
    private final CheckingAccountFinder checkingAccountFinder;
    private final StudentCheckingAccountFinder studentCheckingAccountFinder;
    private final CreditAccountFinder creditAccountFinder;
    private final SavingsAccountCreator savingsAccountCreator;
    private final CheckingAccountCreator checkingAccountCreator;
    private final Transfer transfer;


    public AccountsController(HttpServletRequest request, CreditAccountCreator creditAccountCreator, BalanceModifier balanceModifier, SavingsAccountFinder savingsAccountFinder, CheckingAccountFinder checkingAccountFinder, StudentCheckingAccountFinder studentCheckingAccountFinder, CreditAccountFinder creditAccountFinder, SavingsAccountCreator savingsAccountCreator, CheckingAccountCreator checkingAccountCreator, Transfer transfer) {
        this.request = request;
        this.creditAccountCreator = creditAccountCreator;
        this.balanceModifier = balanceModifier;
        this.savingsAccountFinder = savingsAccountFinder;
        this.checkingAccountFinder = checkingAccountFinder;
        this.studentCheckingAccountFinder = studentCheckingAccountFinder;
        this.creditAccountFinder = creditAccountFinder;
        this.savingsAccountCreator = savingsAccountCreator;
        this.checkingAccountCreator = checkingAccountCreator;
        this.transfer = transfer;
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    @PostMapping("/credit")
    public ResponseEntity<CreditAccount> createCreditAccount(@RequestBody @Valid CreateCreditAccountDto dto) throws OwnerNotFoundException {
        var account = this.creditAccountCreator.execute(dto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/checking")
    public ResponseEntity<BaseDebitAccount> createCheckingAccount(@RequestBody @Valid CreateCheckingAccountDto dto) throws OwnerNotFoundException, UserIsNotStudentException {
        // TODO: Test create checking account/ student account
        var account = this.checkingAccountCreator.execute(dto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/savings")
    public ResponseEntity<SavingsAccount> createSavingsAccount(@RequestBody @Valid CreateSavingsAccountDto dto) throws OwnerNotFoundException {
        var account = this.savingsAccountCreator.execute(dto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
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
    public ResponseEntity<Object> transfer(@RequestBody @Valid TransferDto dto) throws AccountNotFoundException, AccountFrozenException, NotEnoughBalanceException {
        // TODO: return some transfer details object
        this.transfer.execute(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
