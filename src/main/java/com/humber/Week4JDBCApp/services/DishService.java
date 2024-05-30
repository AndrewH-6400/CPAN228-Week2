package com.humber.Week4JDBCApp.services;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    //inject dependency so that getDishes can be accessed
    @Autowired
    private DishRepository dishRepository;

    //Add a method to get all the dishes
    public List<Dish> getAllDishes() {

        return dishRepository.getDishes();
    }

    //save a dish
    public int saveDish(Dish dish){
        return dishRepository.save(dish);
    }
}
