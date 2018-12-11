package com.dicka.springjunctiontable.repository;

import com.dicka.springjunctiontable.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, String> {
}
