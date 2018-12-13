package com.dicka.springjunctiontable.controller;

import com.dicka.springjunctiontable.model.RequestPersonSkill;
import com.dicka.springjunctiontable.service.PersonSkillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class PersonSkillController {

    private final PersonSkillService personSkillService;

    public PersonSkillController(PersonSkillService personSkillService){
        this.personSkillService = personSkillService;
    }

    @PostMapping(value = "/person-skill/create")
    public void createdPersonSkill(@RequestBody RequestPersonSkill requestPersonSkill){
        personSkillService.insertPersonSkill(
                requestPersonSkill.getPersonId(),
                requestPersonSkill.getSkillId()
        );
    }
}
