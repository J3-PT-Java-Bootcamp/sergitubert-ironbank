package com.ironhack.sergitubertironbank.accounts.DebitAccount.services;

import com.ironhack.sergitubertironbank.users.AccountHolder.AccountHolder;

public class UserIsNotStudentException extends Exception {
    public UserIsNotStudentException(Long id, Integer age) {
        super(String.format("User with id: %d is not under %d years old. Current age: %d", id, AccountHolder.MAXIMUM_STUDENT_AGE_THRESHOLD, age));
    }
}
