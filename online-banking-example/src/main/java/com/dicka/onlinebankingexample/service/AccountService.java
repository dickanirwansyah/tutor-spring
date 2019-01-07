package com.dicka.onlinebankingexample.service;

import com.dicka.onlinebankingexample.entity.PrimaryAccount;
import com.dicka.onlinebankingexample.entity.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();

    /** deposit **/
    void deposit(String accountType, double amount, Principal principal);

    /** withdraw **/
    void withdraw(String accountType, double amount, Principal principal);
}
