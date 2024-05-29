package com.humber.Week4JDBCApp.services;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    //Add a method to get all the dishes
    public List<Dish> getAllDishes() {
        return DishRepository.getDishes();
    }
}
