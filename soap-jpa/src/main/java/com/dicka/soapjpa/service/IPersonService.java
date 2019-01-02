package com.dicka.soapjpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dicka.soapjpa.entity.Person;
import com.dicka.soapjpa.repo.PersonRepository;

@Transactional
@Service
public class IPersonService implements PersonService{

	private final PersonRepository personRepository;
	
	@Autowired
	public IPersonService(PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	@Override
	public List<Person> getPersonAll() {
		List<Person> persons = new ArrayList<Person>();
		for (Person person : personRepository.findAll()){
			persons.add(person);
		}
		return persons;
	}

	@Override
	public Person getPersonById(Long personId) {
		Person person = personRepository.findByPersonId(personId);
		return person;
	}

	@Override
	public boolean addPerson(Person person) {
		List<Person> persons = personRepository.findByEmail(person.getEmail());
		if(persons.size() > 0){
			return false;
		}else{
			personRepository.save(person);
			return true;
		}
	}

	@Override
	public void updatePerson(Person person) {
		personRepository.save(person);
	}

	@Override
	public void deletePerson(Person person) {
		personRepository.delete(person);
	}

}
