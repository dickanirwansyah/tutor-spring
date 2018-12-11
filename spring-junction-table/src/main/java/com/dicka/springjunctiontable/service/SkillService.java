package com.dicka.springjunctiontable.service;

import com.dicka.springjunctiontable.entity.Skill;
import com.dicka.springjunctiontable.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    public List<Skill> listSkill(){
        List<Skill> skills = new ArrayList<>();
        for (Skill skill : skillRepository.findAll()){
             skills.add(skill);
        }
        return skills;
    }
}
