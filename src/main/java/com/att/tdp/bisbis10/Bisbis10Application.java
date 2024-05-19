package com.att.tdp.bisbis10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * The main entry point for the bisbis10 application.
 * This Spring Boot application provides functionalities for managing restaurants.
 *
 * @author Shira Cohen
 */

@SpringBootApplication
@EntityScan("com.att.tdp.bisbis10.model")
public class Bisbis10Application {

	public static void main(String[] args) {
		SpringApplication.run(Bisbis10Application.class, args);
	}

}
