package com.humber.Week6JPA.controllers;

import com.humber.Week6JPA.models.Dish;
import com.humber.Week6JPA.services.DishService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @Value("${restaurant.pageSize}")
    private int pageSize;

    //Home Page
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("restaurantName", name);
        return "home";
    }

    //Menu Page
    @GetMapping("/menu/{pageNo}")
    public String getAllDishes(Model model, @RequestParam(required = false) String message,
                               @RequestParam(required = false) String searchedCategory,
                               @RequestParam(required = false) Double searchedPrice,
                               @PathVariable(required = false) int pageNo,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDirection) {

        //give default values to sortField and sortDirection
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        if(searchedCategory != null && searchedPrice != null) {
            //return back the filtered dishes from the service layer
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchedCategory,searchedPrice);
            if(filteredDishes.isEmpty()){
                message = "Data not filtered";
            } else {
                model.addAttribute("dishes", filteredDishes);
                model.addAttribute("message","data filtered successfully!");
                return "menu";
            }
        }
        //pagination info
        Page<Dish> page = dishService.getPaginatedDishes(pageSize,pageNo,sortField,sortDirection);

        model.addAttribute("dishes", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalItems",page.getTotalElements());

        //sorting info
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

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
            return "redirect:/restaurant/menu/1?message=Dish added successfully!";
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
            return "redirect:/restaurant/menu/1?message=Dish deleted successfully!";
        }
        return"redirect:/restaurant/menu/1?message=Dish to be delted does not exist";
    }

    //update a dish (open the update form)
    @GetMapping("/update/{id}")
    public String updateDish(Model model, @PathVariable int id){
        Optional<Dish> dishToUpdate;
        dishToUpdate = dishService.getDishById(id);
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "add-dish";
    }

    //update a dish
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish Updated Successfully!";
    }
}
