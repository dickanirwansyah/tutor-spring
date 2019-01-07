package com.dicka.onlinebankingexample.controller;

import com.dicka.onlinebankingexample.entity.User;
import com.dicka.onlinebankingexample.repository.UserRepository;
import com.dicka.onlinebankingexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService,
                          UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/profile",
        method = RequestMethod.GET)
    public String getProfile(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/profile",
        method = RequestMethod.POST)
    public String actionProfile(@ModelAttribute("user") User user,
                                Model model){
        try{
            User usr = userService.findByUsername(user.getUsername());
            usr.setUsername(user.getUsername());
            usr.setFirstname(user.getFirstname());
            usr.setLastname(user.getLastname());
            usr.setEmail(user.getEmail());
            usr.setPhone(user.getPhone());
            //user.setEnable(true);
            model.addAttribute("user", usr);
            userService.saveUser(usr);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "profile";
    }
}
