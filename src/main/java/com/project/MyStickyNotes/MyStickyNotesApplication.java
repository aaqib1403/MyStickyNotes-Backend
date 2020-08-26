package com.project.MyStickyNotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
public class MyStickyNotesApplication {

	public static void main(String[] args) {
		SpringApplication app =	new SpringApplication(MyStickyNotesApplication.class);;
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "3001"));
		app.run(args);
	}

}
