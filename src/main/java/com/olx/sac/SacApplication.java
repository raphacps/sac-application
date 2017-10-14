package com.olx.sac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SacApplication {

	public static void main(String[] args) {
		SpringApplication.run(SacApplication.class, args);
	}
}
