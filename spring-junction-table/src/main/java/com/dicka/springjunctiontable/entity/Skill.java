package com.dicka.springjunctiontable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "skill")
public class Skill implements Serializable{

    @Id
    @Column(name = "skill_id")
    private String skillId;
    private String nama;

    public Skill(){}

    public Skill(String skillId, String nama){
        this.skillId = skillId;
        this.nama = nama;
    }

    public String getSkillId(){
        return skillId;
    }

    public void setSkillId(String skillId){
        this.skillId = skillId;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }
}
