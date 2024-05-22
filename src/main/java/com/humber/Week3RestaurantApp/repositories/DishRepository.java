package com.humber.Week3RestaurantApp.repositories;

import com.humber.Week3RestaurantApp.models.Dish;

import java.util.List;
import java.util.ArrayList;

public class DishRepository {

    private static List<Dish> dishes = new ArrayList<>();

    static {
        dishes.add(
                Dish.builder()
                        .id(1)
                        .name("Pizza")
                        .category("Veg")
                        .price(10.0)
                    .build()
        );
        dishes.add(
                Dish.builder()
                        .id(2)
                        .name("Burger")
                        .category("Non-Veg")
                        .price(5.0)
                        .build()
        );
        dishes.add(
                Dish.builder()
                        .id(3)
                        .name("Shawarma")
                        .category("Non-Veg")
                        .price(8.0)
                        .build()
        );
        dishes.add(
                Dish.builder()
                        .id(4)
                        .name("Poutine")
                        .category("Veg")
                        .price(12.0)
                        .build()
        );
    }

    public static List<Dish> getDishes(){
        return dishes;
    }
}
