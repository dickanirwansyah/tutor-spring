package com.dicka.onlinebankingexample.service.impl;

import com.dicka.onlinebankingexample.entity.*;
import com.dicka.onlinebankingexample.repository.*;
import com.dicka.onlinebankingexample.service.TransactionService;
import com.dicka.onlinebankingexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PrimaryAccountRepository primaryAccountRepository;

    @Autowired
    private PrimaryTransactionRepository primaryTransactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user
                .getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user
                .getSavingsAccount().getSavingsTransactionList();

        return savingsTransactionList;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionRepository.save(primaryTransaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepository.save(savingsTransaction);
    }

    @Override
    public void savePrimaryWithDrawTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionRepository.save(primaryTransaction);
    }

    @Override
    public void saveSavingsWithDrawTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepository.save(savingsTransaction);
    }

    @Override
    public void betweenAccountTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance()
                    .subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance()
                    .add(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = PrimaryTransaction
                    .builder()
                    .date(date)
                    .description("Between account transfer from "+transferFrom+" to "+ transferTo)
                    .type("Account")
                    .status("Finished")
                    .amount(Double.parseDouble(amount))
                    .availableBalance(primaryAccount.getAccountBalance())
                    .primaryAccount(primaryAccount)
                    .build();

            primaryTransactionRepository.save(primaryTransaction);
        }else if(transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")){

            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance()
                    .add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(primaryAccount.getAccountBalance()
                    .subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = SavingsTransaction
                    .builder()
                    .date(date)
                    .description("Between account transfer from "+transferFrom+" to "+transferTo)
                    .type("Transfer")
                    .status("Finished")
                    .amount(Double.parseDouble(amount))
                    .availableBalance(savingsAccount.getAccountBalance())
                    .savingsAccount(savingsAccount)
                    .build();

            savingsTransactionRepository.save(savingsTransaction);
        }else {
            throw new Exception("Sorry Process Transfer Invalid.");
        }
    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        String userPrincipal = principal.getName();
        List<Recipient> recipientList = recipientRepository.findAll()
                .stream().filter(recipient -> userPrincipal
                        .equals(recipient.getUser().getUsername()))
                .collect(Collectors.toList());

        return recipientList;
    }

    @Override
    public Recipient saveRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return recipientRepository.findByName(recipientName);
    }

    @Override
    public void deleteRecipientByName(String name) {
        recipientRepository.deleteByName(name);
    }

    @Override
    public void toSomeOneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if (accountType.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance()
            .subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = PrimaryTransaction
                    .builder()
                    .date(date)
                    .description("Transfer to recipient "+recipient.getName())
                    .type("Transfer")
                    .status("Finished")
                    .amount(Double.parseDouble(amount))
                    .availableBalance(primaryAccount.getAccountBalance())
                    .primaryAccount(primaryAccount)
                    .build();

            primaryTransactionRepository.save(primaryTransaction);
        }else if(accountType.equalsIgnoreCase("Savings")){
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance()
            .subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = SavingsTransaction
                    .builder()
                    .date(date)
                    .description("Transfer to recipient "+recipient.getName())
                    .type("Transfer")
                    .status("Finished")
                    .amount(Double.parseDouble(amount))
                    .availableBalance(savingsAccount.getAccountBalance())
                    .savingsAccount(savingsAccount)
                    .build();

            savingsTransactionRepository.save(savingsTransaction);
        }
    }
}
