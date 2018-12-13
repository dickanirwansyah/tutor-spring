package com.dicka.springjunctiontable.controller;

import com.dicka.springjunctiontable.entity.Skill;
import com.dicka.springjunctiontable.model.RequestSkill;
import com.dicka.springjunctiontable.model.ResponseSkill;
import com.dicka.springjunctiontable.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/skill")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> listSkill(){
        return skillService.listSkill();
    }

    @PostMapping
    public ResponseSkill createSkill(@RequestBody @Valid  RequestSkill requestSkill,
                                      BindingResult bindingResult){

       return skillService.createNewSkill(requestSkill, bindingResult);
    }
}
