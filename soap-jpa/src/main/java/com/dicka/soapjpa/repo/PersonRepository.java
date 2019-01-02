package com.dicka.soapjpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicka.soapjpa.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	Person findByPersonId(Long personId);
	List<Person> findByEmail(String email);
}
