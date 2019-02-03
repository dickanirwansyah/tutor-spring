package com.dicka.onlinebankingexample.controller;

import com.dicka.onlinebankingexample.entity.PrimaryAccount;
import com.dicka.onlinebankingexample.entity.Recipient;
import com.dicka.onlinebankingexample.entity.SavingsAccount;
import com.dicka.onlinebankingexample.entity.User;
import com.dicka.onlinebankingexample.service.TransactionService;
import com.dicka.onlinebankingexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/transfer")
public class TransferController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccounts(Model model){
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");
        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String actionBetweenAccounts(@ModelAttribute("transferFrom") String transferFrom,
                                        @ModelAttribute("transferTo") String transferTo,
                                        String amount, Principal principal) throws Exception{
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        transactionService.betweenAccountTransfer(transferFrom, transferTo, amount,
                primaryAccount, savingsAccount);

        return "redirect:/userFront";
    }

    @RequestMapping(value = "/recipient", method = RequestMethod.GET)
    public String recipient(Model model, Principal principal){
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        Recipient recipient = new Recipient();
        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);
        return "recipient";
    }

    @RequestMapping(value = "/recipient/save")
    public String saveRecipient(@ModelAttribute("recipient") Recipient recipient,
                                Principal principal){
        User user = userService.findByUsername(principal.getName());
        recipient.setUser(user);
        transactionService.saveRecipient(recipient);

        return "redirect:/transfer/recipient";
    }

    /** edit recipient **/
    @RequestMapping(value = "/recipient/edit")
    public String editRecipient(@RequestParam(value = "recipientName")String recipientName,
                                Model model, Principal principal){

        Recipient recipient = transactionService.findRecipientByName(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);

        return "recipient";
    }

    /** delete recipient **/
    @RequestMapping(value = "/recipient/delete")
    public String deleteRecipient(@RequestParam(value = "recipientName") String recipientName,
                                  Model model, Principal principal){

        transactionService.deleteRecipientByName(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        Recipient recipient = new Recipient();
        model.addAttribute("recipient", recipient);
        model.addAttribute("recipientList", recipientList);

        return "recipient";
    }

    /** transfer to someone else **/
    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.GET)
    public String toSomeoneElse(Model model, Principal principal){
        List<Recipient> recipientList = transactionService.findRecipientList(principal);
        model.addAttribute("recipientList", recipientList);
        model.addAttribute("accountType", "");

        return "toSomeoneElse";
    }

    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.POST)
    public String actionToSomeoneElse(@ModelAttribute("recipientName") String recipientName,
                                      @ModelAttribute("accountType") String accountType,
                                      @ModelAttribute("amount") String amount,
                                      Principal principal){

        User user = userService.findByUsername(principal.getName());
        Recipient recipient = transactionService.findRecipientByName(recipientName);
        transactionService.toSomeOneElseTransfer(recipient, accountType,
                amount, user.getPrimaryAccount(), user.getSavingsAccount());

        return "redirect:/userFront";
    }
}
