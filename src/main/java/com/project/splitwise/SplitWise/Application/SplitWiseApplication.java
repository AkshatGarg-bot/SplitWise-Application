package com.project.splitwise.SplitWise.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project")

public class SplitWiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitWiseApplication.class, args);
	}

}
