package com.dicka.onlinebankingexample.controller;

import com.dicka.onlinebankingexample.service.AccountService;
import com.dicka.onlinebankingexample.service.TransactionService;
import com.dicka.onlinebankingexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public AccountController(AccountService accountService,
                             UserService userService,
                             TransactionService transactionService){

        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
    }


    @RequestMapping(value = "/primaryAccount")
    public String primaryAccount(Model model, Principal principal){
        return "primaryAccount";
    }
}
