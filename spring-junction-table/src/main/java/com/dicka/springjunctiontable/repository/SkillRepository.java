package com.dicka.springjunctiontable.repository;

import com.dicka.springjunctiontable.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, String> {

    //@Query("select s from Specialization s, InfluencerSpecialization isp where s.id=isp.key.specId and isp.key.infId=:infId")
    //List<Specialization> getSpecByInfluencerId(@Param("infId") String infId);

    /** JPQL QUERY **/
    @Query("SELECT s FROM Skill s, PersonSkill ps WHERE s.skillId=ps.personSkillPK.skillId and ps.personSkillPK.personId=:personId")
    List<Skill> findSkillByPersonId(@Param("personId") String personId);
}
