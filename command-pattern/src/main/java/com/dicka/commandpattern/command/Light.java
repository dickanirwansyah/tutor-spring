package com.dicka.commandpattern.command;

public class Light {

	private boolean on;
	
	public void switchOn(){
		this.on = true;
	}
	
	public void swithcOff(){
		this.on = false;
	}
	
}
