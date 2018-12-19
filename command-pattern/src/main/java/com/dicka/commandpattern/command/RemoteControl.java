package com.dicka.commandpattern.command;

/** invoker **/
public class RemoteControl {

	private Command command;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void pressButton(){
		command.execute();
	}
}
