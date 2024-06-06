package com.humber.Week5JPA.controllers;

import com.humber.Week5JPA.models.Dish;
import com.humber.Week5JPA.services.DishService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurant")

public class DishController {

    //Autowired is needed to "inject the variable"
    //Field injecting service into control, this is a dangerous thing to do and is not recommended
    //@Autowired
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }



    @Value("${restaurant.name}")
    private String name;

    //Home Page
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("restaurantName", name);
        return "home";
    }

    //Menu Page
    @GetMapping("/menu")
    public String getAllDishes(Model model, @RequestParam(required = false) String message,
                               @RequestParam(required = false) String searchedCategory,
                               @RequestParam(required = false) Double searchedPrice) {

        if(searchedCategory != null && searchedPrice != null) {
            //return back the filtered dishes from the service layer
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchedCategory,searchedPrice);
            //model.addAttribute("dishes",filteredDishes);
            model.addAttribute("dishes", filteredDishes.isEmpty()?dishService.getAllDishes() : filteredDishes);
            model.addAttribute("message",filteredDishes.isEmpty()?"data was not filtered" : "data filtered successfully!");
            return "menu";
        }
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
        int statusCode = dishService.saveDish(dish);

        if(statusCode == 1){
            return "redirect:/restaurant/menu?message=Dish added successfully!";
        }
        model.addAttribute("error","Price should be less then 15!");
        return "add-dish";
    }

    //delete dish
    //using a path variable
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable int id){
        int deleteStatusCode = dishService.deleteDish(id);
        if(deleteStatusCode == 1){
            return "redirect:/restaurant/menu?message=Dish deleted successfully!";
        }
        return"redirect:/restaurant/menu?message=Dish to be delted does not exist";
    }
}
