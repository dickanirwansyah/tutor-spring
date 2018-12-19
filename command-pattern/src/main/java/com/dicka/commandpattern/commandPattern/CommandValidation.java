package com.dicka.commandpattern.commandPattern;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class CommandValidation extends RuntimeException{

	private Set<ConstraintViolation<?>> constraintViolation;
	
	@SuppressWarnings("unchecked")
	public CommandValidation(){
		this.constraintViolation = constraintViolation;
	}
	
	public Set<ConstraintViolation<?>> getConstraintViolation(){
		return constraintViolation;
	}
	
}
