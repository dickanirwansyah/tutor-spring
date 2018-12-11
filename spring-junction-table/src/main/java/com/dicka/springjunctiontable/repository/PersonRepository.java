package com.dicka.springjunctiontable.repository;

import com.dicka.springjunctiontable.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String>{

    List<Person> findPersonByEmail(String email);
}
