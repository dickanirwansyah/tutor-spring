package com.dicka.springbootdtoangularjs.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "skill")
public class Skill implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;
    private String name;

    @ManyToOne
    private Users users;

    public Skill(){}

    public Skill(String name){
        this.name = name;
    }

    public Skill(String name, Users users){
        this.name = name;
        this.users = users;
    }

    public int getSkillId(){
        return skillId;
    }

    public void setSkillId(int skillId){
        this.skillId = skillId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Users getUsers(){
        return users;
    }

    public void setUsers(Users users){
        this.users = users;
    }
}
