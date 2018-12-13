package com.dicka.springjunctiontable.service;

import com.dicka.springjunctiontable.entity.Skill;
import com.dicka.springjunctiontable.exception.ResourceConflictException;
import com.dicka.springjunctiontable.model.RequestSkill;
import com.dicka.springjunctiontable.exception.ResponseError;
import com.dicka.springjunctiontable.model.ResponseSkill;
import com.dicka.springjunctiontable.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    Map<String, String> errorsValidation;

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

    /** create new skill **/
    public ResponseSkill createNewSkill(RequestSkill requestSkill,
                                        BindingResult bindingResult){

        boolean valid = true;
        if (skillRepository.findById(requestSkill.getSkillId()).isPresent()){
            throw new ResourceConflictException("maaf skill id : "
                    +requestSkill.getSkillId()+" sudah ada.");
        }

        if (bindingResult.hasErrors()){
            errorsValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorsValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        Skill skill = new Skill(
                requestSkill.getSkillId(),
                requestSkill.getNama()
        );

        return new ResponseSkill(
                "SUCCESS",
                "201",
                skill
        );
    }
}
