package com.dicka.demojpainventory.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class CommandValidationException extends RuntimeException{

	private Set<ConstraintViolation<?>> constraintViolation;
	
	@SuppressWarnings("unchecked")
	public CommandValidationException(Set constraintViolation){
		this.constraintViolation = (Set<ConstraintViolation<?>>) constraintViolation;
	}
	
	public Set<ConstraintViolation<?>> getConstraintViolation(){
		return constraintViolation;
	}
}
