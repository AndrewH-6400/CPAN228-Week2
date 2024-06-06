package com.humber.Week5JPA.services;

import com.humber.Week5JPA.models.Dish;
import com.humber.Week5JPA.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

//    field injection
//    @Autowired
//    private DishRepository dishRepository;


    private final DishRepository dishRepository;

    //Constructor injection
    //If you have multiple constructors then this one must have @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    //Add a method to get all the dishes
    public List<Dish> getAllDishes() {
        //type cast list over iterable from findAll()
        return (List<Dish>) dishRepository.findAll();
    }

    //save a dish
//    public Dish saveDish(Dish dish){
//        return dishRepository.save(dish);
//    }
    public int saveDish(Dish dish){
        if(dish.getPrice() < 15){
            dishRepository.save(dish);
            return 1;
        }
        return 0;
    }
}
