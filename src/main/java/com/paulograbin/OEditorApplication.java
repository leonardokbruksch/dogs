package com.paulograbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration // This annotation tells Spring Boot to “guess” how you will want to configure Spring, based on the jar dependencies that you have added.
public class OEditorApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OEditorApplication.class, args);

		System.out.println("connection " + context.getEnvironment().getProperty("mysql_connection"));
		System.out.println("username " + context.getEnvironment().getProperty("mysql_username"));
		System.out.println("password " + context.getEnvironment().getProperty("mysql_password"));

//		String[] beanNames = context.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
	}
}
