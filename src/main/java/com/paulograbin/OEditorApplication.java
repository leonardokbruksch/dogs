package com.paulograbin;

import com.paulograbin.annotations.GrabinClassAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.util.Arrays;

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
