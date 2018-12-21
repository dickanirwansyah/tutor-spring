package com.dicka.springbootdemoajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class ViewController {

    @RequestMapping(value = "/index")
    public String getIndex(){
        return "index";
    }

}
