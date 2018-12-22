package com.dicka.commandpattern.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getInde(Model model){
        //model.addAttribute("title", "Thymeleaf Title");
        return "index";
    }
}
