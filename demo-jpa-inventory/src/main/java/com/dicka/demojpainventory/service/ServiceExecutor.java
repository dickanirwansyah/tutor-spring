package com.dicka.demojpainventory.service;

import com.dicka.demojpainventory.command.Command;

public interface ServiceExecutor {

	<T, R extends ServiceRequest> T execute(Class<? extends Command<T, R>> commandClass, R request);
	
}
