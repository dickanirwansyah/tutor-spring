package com.dicka.springjunctiontable.model;

import com.dicka.springjunctiontable.entity.Person;
import com.dicka.springjunctiontable.entity.PersonSkill;
import com.dicka.springjunctiontable.entity.Skill;

import java.util.List;

public class ResponseDataPerson {

    private Person person;
    private List<Skill> skills;

    public ResponseDataPerson(){}

    public ResponseDataPerson(Person person, List<Skill> skills){
        this.person = person;
        this.skills = skills;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

   public List<Skill> getSkills(){
        return skills;
   }

   public void setSkills(List<Skill> skills){
       this.skills = skills;
   }
}
