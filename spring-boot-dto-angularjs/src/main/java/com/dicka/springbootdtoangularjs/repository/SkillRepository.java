package com.dicka.springbootdtoangularjs.repository;

import com.dicka.springbootdtoangularjs.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
