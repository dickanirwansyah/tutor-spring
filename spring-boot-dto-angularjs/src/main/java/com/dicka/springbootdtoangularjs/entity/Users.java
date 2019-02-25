package com.dicka.springbootdtoangularjs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private int usersId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Skill> skills = new LinkedList<>();

    public Users(){}

    public Users(String name, List<Skill> skills){
        this.name = name;
        this.skills = skills;
    }

    public int getUsersId(){
        return usersId;
    }

    public void setUsersId(int usersId){
        this.usersId = usersId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills){
        this.skills = skills;
    }
}
