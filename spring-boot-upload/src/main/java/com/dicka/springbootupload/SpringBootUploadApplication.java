package com.dicka.springbootupload;

import com.dicka.springbootupload.uploaded.FileStorageProperties;
import com.dicka.springbootupload.uploads.config.ConfigFileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class,
		ConfigFileStorageProperties.class
})
public class SpringBootUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadApplication.class, args);
	}

}

