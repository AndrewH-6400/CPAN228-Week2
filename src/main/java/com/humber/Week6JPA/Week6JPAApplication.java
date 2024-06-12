package com.humber.Week6JPA;

import com.humber.Week6JPA.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week6JPAApplication implements CommandLineRunner {

	// injecting service - constructor injection
	private final DishService dishService;
	public Week6JPAApplication(DishService dishService) {
		this.dishService = dishService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Week6JPAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
