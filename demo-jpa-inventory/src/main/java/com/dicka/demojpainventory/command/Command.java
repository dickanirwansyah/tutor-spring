package com.dicka.demojpainventory.command;

import com.dicka.demojpainventory.service.ServiceRequest;

public interface Command<RESULT, REQUEST extends ServiceRequest>{

	RESULT execute(REQUEST request);
	
}
