package com.dicka.springjunctiontable.service;

import com.dicka.springjunctiontable.constant.ConstanMessage;
import com.dicka.springjunctiontable.entity.Person;
import com.dicka.springjunctiontable.entity.Skill;
import com.dicka.springjunctiontable.exception.ResourceConflictException;
import com.dicka.springjunctiontable.exception.ResourceNotFoundException;
import com.dicka.springjunctiontable.model.RequestPerson;
import com.dicka.springjunctiontable.model.ResponseDataPerson;
import com.dicka.springjunctiontable.model.ResponsePerson;
import com.dicka.springjunctiontable.repository.PersonRepository;
import com.dicka.springjunctiontable.repository.PersonSkillRepository;
import com.dicka.springjunctiontable.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonSkillRepository personSkillRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         PersonSkillRepository personSkillRepository,
                         SkillRepository skillRepository){

        this.personRepository = personRepository;
        this.personSkillRepository = personSkillRepository;
        this.skillRepository = skillRepository;
    }

    public List<Person> listPerson(){
        List<Person> persons = new ArrayList<>();
        for (Person person : personRepository.findAll()){
            persons.add(person);
        }
        return persons;
    }

    /** cari person dan skill berdasarkan id person**/
    public ResponseDataPerson getPersonById(String personId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("person email : "
                        +personId+" tidak ditemukan."));

        List<Skill> skills = new ArrayList<>();
        for (Skill skill : skillRepository.findSkillByPersonId(personId)){
            skills.add(skill);
        }

        return new ResponseDataPerson(person, skills);
    }

    /** create person **/
    public ResponsePerson createPerson(RequestPerson requestPerson){

        Person person;
        if (personRepository.findById(requestPerson.getEmail()).isPresent()){
            throw new ResourceConflictException("maaf email : "+requestPerson.getEmail()+ " " +
                    "sudah ada.");
        }else {
            person = new Person(
                    requestPerson.getEmail(),
                    requestPerson.getNama(),
                    requestPerson.getPhone()
                    /** another entity **/
            );

            /** create new nasabah **/
            personRepository.save(person);
        }

        return new ResponsePerson(
                "CREATED",
                "201",
                person
        );
    }

    public ResponsePerson updatePerson(String email, RequestPerson requestPerson){

        Person person = personRepository.findById(email)
                .map(currentData -> {
                    currentData.setNama(requestPerson.getNama());
                    currentData.setPhone(requestPerson.getPhone());
                    return personRepository.save(currentData);
                }).orElseThrow(() -> new ResourceNotFoundException("maaf email : "
                        +email +" tidak ditemukan"));

        return new ResponsePerson("SUCCESS", "200", person);
    }
}
