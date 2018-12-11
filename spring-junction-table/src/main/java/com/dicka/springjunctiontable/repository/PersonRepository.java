package com.dicka.springjunctiontable.repository;

import com.dicka.springjunctiontable.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String>{
}
