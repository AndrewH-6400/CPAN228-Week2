package com.humber.Week7Security.controllers;

import com.humber.Week7Security.models.Dish;
import com.humber.Week7Security.services.DishService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/restaurant/admin")
public class AdminController {


    //Autowired is needed to "inject the variable"
    //Field injecting service into control, this is a dangerous thing to do and is not recommended
    //@Autowired
    private final DishService dishService;

    public AdminController(DishService dishService) {
        this.dishService = dishService;
    }


    @Value("${restaurant.name}")
    private String name;

    @Value("${restaurant.pageSize}")
    private int pageSize;


    //open up add-dish page
    @GetMapping("/add-dish")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        return "admin/add-dish";
    }

    //save the dish
    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute Dish dish, Model model) {
        //saving in db
        int statusCode = dishService.saveDish(dish);

        if (statusCode == 1) {
            return "redirect:/restaurant/menu/1?message=Dish added successfully!";
        }
        model.addAttribute("error", "Price should be less then 15!");
        return "admin/add-dish";
    }

    //delete dish
    //using a path variable
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable int id) {
        int deleteStatusCode = dishService.deleteDish(id);
        if (deleteStatusCode == 1) {
            return "redirect:/restaurant/menu/1?message=Dish deleted successfully!";
        }
        return "redirect:/restaurant/menu/1?message=Dish to be delted does not exist";
    }

    //update a dish (open the update form)
    @GetMapping("/update/{id}")
    public String updateDish(Model model, @PathVariable int id) {
        Optional<Dish> dishToUpdate;
        dishToUpdate = dishService.getDishById(id);
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "admin/add-dish";
    }

    //update a dish
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish Updated Successfully!";
    }
}
