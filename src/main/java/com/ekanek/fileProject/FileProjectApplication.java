package com.ekanek.fileProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FileProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileProjectApplication.class, args);
	}

}
