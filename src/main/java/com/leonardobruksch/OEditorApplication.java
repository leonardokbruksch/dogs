package com.leonardobruksch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration // This annotation tells Spring Boot to “guess” how you will want to configure Spring, based on the jar dependencies that you have added.
public class OEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OEditorApplication.class, args);
	}

	@Bean
	@ConditionalOnClass(String.class)
	public String aString() {
		System.out.println("A string has been returned");

		return "aString";
	}

}
