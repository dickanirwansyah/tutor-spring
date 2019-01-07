package com.dicka.commandpattern.commandPattern;

public interface ServiceExecutor {

	<T, R extends ServiceRequest> T execute(Class<? extends Command<T, R>> commandClass, R request);
 	
}
