package com.dicka.springjunctiontable;

import com.dicka.springjunctiontable.entity.PersonSkill;
import com.dicka.springjunctiontable.entity.Skill;
import com.dicka.springjunctiontable.repository.PersonSkillRepository;
import com.dicka.springjunctiontable.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJunctionTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJunctionTableApplication.class, args);
	}
}


@Component
class CommandLineRunners implements CommandLineRunner{

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Skill> skills = new ArrayList<>();
		for (Skill skill : skillRepository
				.findSkillByPersonId("dickanirwansyah@gmail.com")){
			skills.add(skill);
		}
		System.out.println("data skill dicka: "+skills);
	}
}