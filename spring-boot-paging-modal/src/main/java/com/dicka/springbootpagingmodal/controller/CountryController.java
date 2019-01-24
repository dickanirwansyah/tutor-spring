package com.dicka.springbootpagingmodal.controller;

import com.dicka.springbootpagingmodal.entity.Country;
import com.dicka.springbootpagingmodal.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(value = "/")
    public String getIndex(Model model,
                           @RequestParam(defaultValue = "0") int page){
        /** paging **/
        model.addAttribute("data", countryRepository
                .findAll(new PageRequest(page, 4)));
        Country country = new Country();
        model.addAttribute("pagingActive", page);
        return "index";
    }

    @PostMapping(value = "/save")
    public String saveContry(Country country){
        countryRepository.save(country);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String deleteCountry(Integer id){
        Country country = countryRepository.findCountryById(id);
        countryRepository.delete(country);
        return "redirect:/";
    }

    @GetMapping(value = "/findone")
    @ResponseBody
    public Country findOne(Integer id){
        return countryRepository.findCountryById(id);
    }
}
