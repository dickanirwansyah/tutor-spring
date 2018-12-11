package com.dicka.springjunctiontable.service;

import com.dicka.springjunctiontable.entity.PersonSkill;
import com.dicka.springjunctiontable.repository.PersonSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonSkillService {

    private final PersonSkillRepository personSkillRepository;

    @Autowired
    public PersonSkillService(PersonSkillRepository personSkillRepository){
        this.personSkillRepository = personSkillRepository;
    }

    public void insertPersonSkill(String personId, List<String> skillId){
        for (String getSkill: skillId){
            PersonSkill personSkill = new PersonSkill();
            personSkill.getPersonSkillPK().setPersonId(personId);
            personSkill.getPersonSkillPK().setSkillId(getSkill);
            personSkillRepository.save(personSkill);
        }
    }
}
