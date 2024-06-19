package com.humber.Week7Security;

import com.humber.Week7Security.models.Dish;
import com.humber.Week7Security.services.DishService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week7Security implements CommandLineRunner {

	// injecting service - constructor injection
	private final DishService dishService;
	public Week7Security(DishService dishService) {
		this.dishService = dishService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Week7Security.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello from command line runner!");

		dishService.saveDish(new Dish(1, "Pizza", "Non-veg",12.0));
		dishService.saveDish(new Dish(2, "Burger", "Non-veg",10.0));
		dishService.saveDish(new Dish(3, "Pasta", "Veg",13.0));
		dishService.saveDish(new Dish(4, "Sandwich", "Non-veg",8.0));
		dishService.saveDish(new Dish(5, "Salad", "Veg",8.0));
		dishService.saveDish(new Dish(6, "Momo", "Non-veg",9.0));
		dishService.saveDish(new Dish(7, "Fish", "Non-veg",14.0));
		dishService.saveDish(new Dish(8, "Pork", "Non-veg",13.0));
		dishService.saveDish(new Dish(9, "Beef", "Non-veg",11.0));
		dishService.saveDish(new Dish(10, "Mutton", "Non-veg",14.0));
		dishService.saveDish(new Dish(11, "Grilled Cheese", "Veg",7.0));
		dishService.saveDish(new Dish(12, "Mushroom Udon", "Veg",12.0));
	}
}
