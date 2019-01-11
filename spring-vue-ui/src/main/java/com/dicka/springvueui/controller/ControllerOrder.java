package com.dicka.springvueui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerOrder {

    @RequestMapping(value = "/page/order")
    public String getIndex(){
        return "index";
    }

}
