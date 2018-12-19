package com.dicka.commandpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dicka.commandpattern.command.Command;
import com.dicka.commandpattern.command.Light;
import com.dicka.commandpattern.command.LightOffCommand;
import com.dicka.commandpattern.command.LightOnCommand;
import com.dicka.commandpattern.command.RemoteControl;

@SpringBootApplication
public class CommandPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandPatternApplication.class, args);
		/** command pattern **/
		RemoteControl controlSystem = new RemoteControl();
		Light light = new Light();
		
		Command commandOn = new LightOnCommand(light);
		Command commandOff = new LightOffCommand(light);
		
		/** on **/
		controlSystem.setCommand(commandOn);
		controlSystem.pressButton();
		
		/** off **/
		controlSystem.setCommand(commandOff);
		controlSystem.pressButton();
	}

}

