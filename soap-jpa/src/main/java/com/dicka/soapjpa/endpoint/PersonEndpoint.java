package com.dicka.soapjpa.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dicka.soapjpa.entity.Person;
import com.dicka.soapjpa.service.PersonService;
import com.myhost.soap_jpa.person_ws.AddRequestPerson;
import com.myhost.soap_jpa.person_ws.AddResponsePerson;
import com.myhost.soap_jpa.person_ws.GetRequestPersonAll;
import com.myhost.soap_jpa.person_ws.GetRequestPersonById;
import com.myhost.soap_jpa.person_ws.GetResponsePersonAll;
import com.myhost.soap_jpa.person_ws.GetResponsePersonById;
import com.myhost.soap_jpa.person_ws.PersonInfo;
import com.myhost.soap_jpa.person_ws.ServiceStatus;
import com.myhost.soap_jpa.person_ws.UpdateRequestPerson;
import com.myhost.soap_jpa.person_ws.UpdateResponsePerson;

@Endpoint
public class PersonEndpoint {
	
	/** person endpoint - controller person **/
	private static final String URI_NAMESPACE = "http://myhost.com/soap-jpa/person-ws";
	private final PersonService personService;
	
	@Autowired
	public PersonEndpoint(PersonService personService){
		this.personService = personService;
	}
	
	
	@PayloadRoot(namespace = URI_NAMESPACE, localPart = "getRequestPersonAll")
	@ResponsePayload
	public GetResponsePersonAll findAll(){
		GetResponsePersonAll personAll = new GetResponsePersonAll();
		List<PersonInfo> personsInfo = new ArrayList<PersonInfo>();
		List<Person> persons = personService.getPersonAll();
		for (int i=0; i<persons.size(); i++){
			PersonInfo personInfo = new PersonInfo();
			BeanUtils.copyProperties(persons.get(i), personInfo);
			personsInfo.add(personInfo);
		}
		personAll.getPersonInfo()
			.addAll(personsInfo);
		return personAll;
	}
	
	@PayloadRoot(namespace = URI_NAMESPACE, localPart = "getRequestPersonById")
	@ResponsePayload
	public GetResponsePersonById findById(@RequestPayload GetRequestPersonById request){
		GetResponsePersonById responsePerson = new GetResponsePersonById();
		PersonInfo personInfo = new PersonInfo();
		BeanUtils.copyProperties(personService.getPersonById(request.getPersonId()), 
				personInfo);
		responsePerson.setPersonInfo(personInfo);
		return responsePerson;
	}
	
	@PayloadRoot(namespace = URI_NAMESPACE, localPart = "addRequestPerson")
	@ResponsePayload
	public AddResponsePerson save(@RequestPayload AddRequestPerson request){
		AddResponsePerson responsePerson = new AddResponsePerson();
		ServiceStatus serviceStatus = new ServiceStatus();
		Person person = new Person();
		person.setFirstname(request.getFirstname());
		person.setLastname(request.getLastname());
		person.setEmail(request.getEmail());
		person.setPhone(request.getPhone());
		person.setAddress(request.getAddress());
		
		boolean validate = personService.addPerson(person);
		
		if(validate == false){
			serviceStatus.setCode("409");
			serviceStatus.setMessage("conflict");
			responsePerson.setServiceStatus(serviceStatus);
		}else{
			PersonInfo personInfo = new PersonInfo();
			BeanUtils.copyProperties(person, personInfo);
			responsePerson.setPersonInfo(personInfo);
			serviceStatus.setCode("200");
			serviceStatus.setMessage("success");
			responsePerson.setServiceStatus(serviceStatus);
		}
		
		return responsePerson;
	}
	
	@PayloadRoot(namespace = URI_NAMESPACE, localPart = "updateRequestPerson")
	@ResponsePayload
	public UpdateResponsePerson update(@RequestPayload UpdateRequestPerson request){
		UpdateResponsePerson responsePerson = new UpdateResponsePerson();
		ServiceStatus serviceStatus = new ServiceStatus();
		Person person = new Person();
		
		BeanUtils.copyProperties(request.getPersonInfo(), person);
		personService.updatePerson(person);
		serviceStatus.setCode("200");
		serviceStatus.setMessage("success");
		responsePerson.setServiceStatus(serviceStatus);
		return responsePerson;
	}
}
