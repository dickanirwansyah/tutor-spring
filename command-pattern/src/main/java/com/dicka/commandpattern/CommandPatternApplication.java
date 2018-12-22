package com.dicka.commandpattern;

import com.dicka.commandpattern.repository.MedsosRepository;
import com.dicka.commandpattern.repository.PenggunaMedsosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dicka.commandpattern.command.Command;
import com.dicka.commandpattern.command.Light;
import com.dicka.commandpattern.command.LightOffCommand;
import com.dicka.commandpattern.command.LightOnCommand;
import com.dicka.commandpattern.command.RemoteControl;
import org.springframework.stereotype.Component;

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

/** coba query
@Component
class ExampleTestCommand implements CommandLineRunner{

	@Autowired
	private MedsosRepository medsosRepository;

	@Autowired
	private PenggunaMedsosRepository penggunaMedsosRepository;

	@Override
	public void run(String... args) throws Exception {
		//this.medsosRepository.deleteByEmail("dickanirwansyah@gmail.com", "m001");
		penggunaMedsosRepository.deleteByEmail("dickanirwansyah@gmail.com",
				"m001");
	}
}
**/