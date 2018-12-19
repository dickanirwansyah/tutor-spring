package com.dicka.commandpattern.commandPattern;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractCommand<RESULT, REQUEST extends ServiceRequest> 
	implements Command<RESULT, REQUEST>, ApplicationContextAware, InitializingBean{

	protected Validator validator;
	protected ApplicationContext applicationContext;
	
	@Override
	public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		this.applicationContext = applicationContext;
	}
	
	@Override
	public final void afterPropertiesSet() throws Exception{
		this.validator = applicationContext.getBean(Validator.class);
	}
	
	@Override
	public final RESULT execute(REQUEST request){
		Set<ConstraintViolation<REQUEST>> constraintViolation = validator
				.validate(request);
		
		if (constraintViolation.isEmpty()){
			return doExecute(request);
		}else{
			return null;
		}
	}
	
	public abstract RESULT doExecute(REQUEST request);
}
