package com.dicka.springbootupload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerIndex {

    @GetMapping(value = ConstantApp.INDEX_URI)
    public String getIndex(Model model){
        model.addAttribute("title", "Example Upload");
        return "index";
    }
}
