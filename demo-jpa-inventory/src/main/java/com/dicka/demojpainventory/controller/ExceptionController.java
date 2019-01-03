package com.dicka.demojpainventory.controller;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.demojpainventory.exception.CommandValidationException;
import com.dicka.demojpainventory.model.Response;

/** GLOBAL ERRORS **/

@RestController
public class ExceptionController {
	
	/** Response by packaging demojpainventory.model **/
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response<Object> handleThrowable(Throwable throwable){
		return Response.status(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
	}
	
	@ExceptionHandler(CommandValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> handleCommandValidation(CommandValidationException e){
		Response<Object> response = Response.status(HttpStatus.BAD_REQUEST, null);
		
		e.getConstraintViolation()
			.forEach(violation -> {
				for(String attribute : getAttributes(violation)){
					response.addError(attribute, violation.getMessage());
				}
			});
		return response;
	}
	
	private String[] getAttributes(ConstraintViolation<?> constraintViolation){
		String[] values = (String[]) constraintViolation
				.getConstraintDescriptor().getAttributes()
					.get("path");
		
		if(values == null || values.length == 0){
			/** getAttribyesFromPath **/
			return getAttributesFromPath(constraintViolation);
		}else{
			return values;
		}
	}
	
	private String[] getAttributesFromPath(ConstraintViolation<?> constraintViolation){
		Path path = constraintViolation.getPropertyPath();
		
		StringBuilder builder = new StringBuilder();
		path.forEach(node -> {
			if (node.getName() != null){
				if(builder.length() > 0){
					builder.append(".");
				}
				builder.append(node.getName());
			}
		});
		
		return new String[] {
				builder.toString()
		};
	}
}
