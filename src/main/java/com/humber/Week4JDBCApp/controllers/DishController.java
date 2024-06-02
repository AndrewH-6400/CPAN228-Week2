package com.humber.Week4JDBCApp.controllers;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAllDishes(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("message",message);
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
        //saving in db
        //1 is good anything else is error
        int result = dishService.saveDish(dish);

        //a result of 1 means the save was successful
        if(result==1){
            //redirect to menu
            return "redirect:/restaurant/dishes?message=Dish added successfully!";
        } else{
            //the only error that can be handled is a pricing error, but more could be added as else if statements
            model.addAttribute("error", "Price should be less than 20!");
            return "add-dish";
        }



    }
}
