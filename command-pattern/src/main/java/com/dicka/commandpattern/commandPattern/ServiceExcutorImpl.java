package com.dicka.commandpattern.commandPattern;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ServiceExcutorImpl implements ServiceExecutor, ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}

	@Override
	public <T, R extends ServiceRequest> T execute(
			Class<? extends Command<T, R>> commandClass, R request) {
		
		Command<T, R> command = applicationContext.getBean(commandClass);
		return command.execute(request);
	}

}
