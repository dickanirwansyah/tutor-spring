package com.dicka.springjunctiontable.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PersonSkillPK implements Serializable{

    @Column(name = "person_id")
    private String personId;

    @Column(name = "skill_id")
    private String skillId;

}
