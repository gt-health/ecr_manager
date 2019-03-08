package edu.gatech.chai.ecr.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration
@EnableScheduling
@EntityScan("edu.gatech.chai.ecr.jpa.model")
@EnableJpaRepositories("edu.gatech.chai.ecr.jpa.repo")
@ComponentScan("edu.gatech.chai.ecr.repository.controller")
@ComponentScan("edu.gatech.chai.ecr.repository")
@ComponentScan("edu.gatech.chai.ecr.repository.service")
@SpringBootApplication
public class ApplicationTest extends SpringBootServletInitializer{

	private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationTest.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ApplicationTest.class, args);
	}
	
}