package com.dicka.springjunctiontable.controller;

import com.dicka.springjunctiontable.entity.Person;
import com.dicka.springjunctiontable.model.RequestPerson;
import com.dicka.springjunctiontable.model.RequestRegex;
import com.dicka.springjunctiontable.model.ResponseDataPerson;
import com.dicka.springjunctiontable.model.ResponsePerson;
import com.dicka.springjunctiontable.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    /** new create **/
    @PostMapping(value = "/person-create")
    public ResponsePerson createPerson(@RequestBody RequestPerson requestPerson){
        return personService.createPerson(requestPerson);
    }

    /** create person with regex **/
    @PostMapping(value = "/person-create-regex")
    public ResponsePerson createPersonRegex(@RequestBody RequestRegex regex){
        return personService.createPersonWithSplitRegexComma(regex);
    }

    @GetMapping(value = "/person-list")
    public List<Person> personList(){
        return personService.listPerson();
    }

    /** get person id by email **/
    @GetMapping(value = "/person/{email}/skills")
    public ResponseDataPerson findPersonAndSkillByPersonId(@PathVariable String email){
        return personService.getPersonById(email);
    }

    /** update **/
    @PostMapping(value = "/person-create/{email}")
    public ResponsePerson updatePerson(@PathVariable("email") String email,
                                       @RequestBody RequestPerson requestPerson){

        return personService.updatePerson(email, requestPerson);
    }
}
