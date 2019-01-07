package com.dicka.onlinebankingexample.service;

import com.dicka.onlinebankingexample.entity.*;

import java.security.Principal;
import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void savePrimaryWithDrawTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingsWithDrawTransaction(SavingsTransaction savingsTransaction);

    /** TRANSFER MONEY **/
    void betweenAccountTransfer(String transferFrom, String transferTo,
                                String amount, PrimaryAccount primaryAccount,
                                SavingsAccount savingsAccount) throws Exception;

    /** LIST NASABAH PENERIMA BERDASARKAN PRINCIPAL **/
    List<Recipient> findRecipientList(Principal principal);

    Recipient saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipientByName(String name);

    /** transfer dana **/
    void toSomeOneElseTransfer(Recipient recipient, String accountType, String amount,
                               PrimaryAccount primaryAccount, SavingsAccount savingsAccount);
}

