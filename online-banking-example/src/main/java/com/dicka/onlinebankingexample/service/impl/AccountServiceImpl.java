package com.dicka.onlinebankingexample.service.impl;

import com.dicka.onlinebankingexample.entity.*;
import com.dicka.onlinebankingexample.repository.PrimaryAccountRepository;
import com.dicka.onlinebankingexample.repository.SavingsAccountRepository;
import com.dicka.onlinebankingexample.service.AccountService;
import com.dicka.onlinebankingexample.service.TransactionService;
import com.dicka.onlinebankingexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static int staticAccountNumber = 11223145;

    @Autowired
    private PrimaryAccountRepository primaryAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;


    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = PrimaryAccount
                .builder()
                .accountBalance(new BigDecimal(0.0))
                .accountNumber(generatedAccountNumber())
                .build();

        primaryAccountRepository.save(primaryAccount);
        return primaryAccountRepository.findByAccountNumber(
                primaryAccount.getAccountNumber()
        );
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = SavingsAccount
                .builder()
                .accountBalance(new BigDecimal(0.0))
                .accountNumber(generatedAccountNumber())
                .build();

        savingsAccountRepository.save(savingsAccount);
        return savingsAccountRepository.findByAccountNumber(
                savingsAccount.getAccountNumber()
        );
    }

    /** DEPOSIT **/
    @Override
    public void deposit(String accountType, double amount, Principal principal) {
        /** check user credential security **/
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(
                    primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = PrimaryTransaction
                    .builder()
                    .date(date)
                    .description("Deposit to Primary Account")
                    .type("Account")
                    .status("Finished")
                    .amount(amount)
                    .availableBalance(primaryAccount.getAccountBalance())
                    .primaryAccount(primaryAccount)
                    .build();

            transactionService.savePrimaryDepositTransaction(primaryTransaction);
        }else if(accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance()
            .add(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = SavingsTransaction
                    .builder()
                    .date(date)
                    .description("Deposit to Savings Account")
                    .type("Account")
                    .status("Finished")
                    .amount(amount)
                    .availableBalance(savingsAccount.getAccountBalance())
                    .savingsAccount(savingsAccount)
                    .build();

            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    /** with Draw balance **/
    @Override
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance()
            .subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = PrimaryTransaction
                    .builder()
                    .date(date)
                    .description("Withdraw from Primary account")
                    .type("Account")
                    .status("Finished")
                    .amount(amount)
                    .availableBalance(primaryAccount.getAccountBalance())
                    .primaryAccount(primaryAccount)
                    .build();
            transactionService.savePrimaryWithDrawTransaction(primaryTransaction);

        }else if (accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance()
            .subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = SavingsTransaction
                    .builder()
                    .date(date)
                    .description("Withdraw from Savings account")
                    .type("Account")
                    .status("Finished")
                    .amount(amount)
                    .availableBalance(savingsAccount.getAccountBalance())
                    .savingsAccount(savingsAccount)
                    .build();

            transactionService.saveSavingsWithDrawTransaction(savingsTransaction);
        }
    }

    private int generatedAccountNumber(){
        return ++staticAccountNumber;
    }
}
