package com.dicka.springbootjquerythymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerPage {

    @GetMapping(value = {"/", "/index", "/home"})
    public String getIndex(Model model){
        model.addAttribute("title", "Example Jquery Thymelaf");
        return "index";
    }
}
