package com.dicka.soapjpa.service;

import java.util.List;

import com.dicka.soapjpa.entity.Person;

public interface PersonService {

	List<Person> getPersonAll();
	Person getPersonById(Long personId);
	boolean addPerson(Person person);
	void updatePerson(Person person);
	void deletePerson(Person person);
}
