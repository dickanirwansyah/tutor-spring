package com.dicka.springjunctiontable.repository;

import com.dicka.springjunctiontable.entity.PersonSkill;
import com.dicka.springjunctiontable.entity.PersonSkillPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonSkillRepository extends JpaRepository<PersonSkill, PersonSkill> {

    List<PersonSkill> findPersonSkillByPersonSkillPK(String personSkillPK);
}
