package com.humber.Week3RestaurantApp.controllers;

import com.humber.Week3RestaurantApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
