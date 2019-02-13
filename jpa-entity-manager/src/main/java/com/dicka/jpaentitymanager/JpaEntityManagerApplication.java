package com.dicka.jpaentitymanager;

import com.dicka.jpaentitymanager.entity.Pengguna;
import com.dicka.jpaentitymanager.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JpaEntityManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaEntityManagerApplication.class, args);
	}

}

interface PenggunaRepository extends JpaRepository<Pengguna, String>{

}


/**
@Component
class TestCommandLineRunnerData implements CommandLineRunner{

	@Autowired
	PenggunaRepository penggunaRepository;

	@Autowired
	PenggunaService penggunaService;

	@Override
	public void run(String... args) throws Exception {

		Pengguna pengguna = new Pengguna();
		pengguna.setNama("adelia");
		pengguna.setAlamat("cilandak");
		pengguna.setEmail("dicka@gmail.com");
		pengguna.setPenggunaId("p-004");
		pengguna.setGoldarah("AB");
		pengguna.setTelepon("087898989293");


		Pengguna pengguna1 = new Pengguna();
		pengguna1.setNama("jupri");
		pengguna1.setAlamat("ciputat");
		pengguna1.setEmail("jupri@gmail.com");
		pengguna1.setPenggunaId("p-008");
		pengguna1.setGoldarah("B");
		pengguna1.setTelepon("08789228989293");
		penggunaService.createPengguna(pengguna1);

		//penggunaRepository.save(pengguna1);
		//penggunaRepository.save(pengguna);
	}
}
**/

