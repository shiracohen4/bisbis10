package com.att.tdp.bisbis10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.att.tdp.bisbis10.model")
public class Bisbis10Application {

	public static void main(String[] args) {
		SpringApplication.run(Bisbis10Application.class, args);
	}

}
