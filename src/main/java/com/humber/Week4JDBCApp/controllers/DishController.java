package com.humber.Week4JDBCApp.controllers;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant")

public class DishController {

    //Autowired is needed to "inject the variable"
    //Field injecting service into control, this is a dangerous thing to do and is not recommended
    @Autowired
    private DishService dishService;

    @Value("${restaurant.name}")
    private String name;

    //Home Page
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("restaurantName", name);
        return "home";
    }

    //Menu Page
    @GetMapping("/dishes")
    public String getAllDishes(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "menu";
    }

    //open up add-dish page
    @GetMapping("/add-dish")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
    }

    //save the dish
    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute Dish dish, Model model) {
        //this should be moved to the service layer
        if(dish.getPrice()>10){
            model.addAttribute("error", "Price should be less than 10!");
            return "add-dish";
        }

        //saving in db
        //1 is good anything else is error
        int result = dishService.saveDish(dish);

        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("message","Dish added successfully!");
        //redirect to menu
        return "menu";
//        return "redirect:/restaurant/dishes";
    }
}
