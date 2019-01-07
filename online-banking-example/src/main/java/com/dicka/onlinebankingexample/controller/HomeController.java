package com.dicka.onlinebankingexample.controller;

import com.dicka.onlinebankingexample.entity.PrimaryAccount;
import com.dicka.onlinebankingexample.entity.SavingsAccount;
import com.dicka.onlinebankingexample.entity.User;
import com.dicka.onlinebankingexample.entity.secure.UserRole;
import com.dicka.onlinebankingexample.repository.RoleRepository;
import com.dicka.onlinebankingexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public HomeController(UserService userService, RoleRepository roleRepository){
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public String getHome(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping(value = "/signup")
    public String getSignup(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    /** action singup **/
    @PostMapping(value = "/signup")
    public String postSingup(@ModelAttribute("user") User user, Model model){

        if (userService.checkUserExists(user.getUsername(), user.getEmail())){

            if (userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        }else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleRepository.findByName("ROLE_USER")));
            userService.createUser(user, userRoles);
            return "redirect:/";
        }
    }

    /** user front or default success link **/
    @GetMapping(value = "/userFront")
    public String getUserFront(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "userFront";
    }
}
