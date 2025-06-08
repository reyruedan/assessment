package com.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AssessmentApplication.class);
		app.run(args);
	}

}
