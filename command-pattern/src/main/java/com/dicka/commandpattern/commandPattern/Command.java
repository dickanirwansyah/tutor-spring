package com.dicka.commandpattern.commandPattern;


public interface Command<RESULT, REQUEST extends ServiceRequest> {

	RESULT execute(REQUEST request);
	
}
