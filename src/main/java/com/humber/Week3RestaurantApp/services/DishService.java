package com.humber.Week3RestaurantApp.services;

import com.humber.Week3RestaurantApp.models.Dish;
import com.humber.Week3RestaurantApp.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    //Add a method to get all the dishes
    public List<Dish> getAllDishes() {
        return DishRepository.getDishes();
    }
}
