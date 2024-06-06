package com.humber.Week5JPA.services;

import com.humber.Week5JPA.models.Dish;
import com.humber.Week5JPA.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public int saveDish(Dish dish){
        if(dish.getPrice() < 15){
            dishRepository.save(dish);
            return 1;
        }
        return 0;
    }
    //get filtered dishes
    public List<Dish> getFilteredDishes(String searchedCategory,Double searchedPrice){
        return dishRepository.findByCategoryAndPrice(searchedCategory, searchedPrice);
    }

    //delete a dish
    public int deleteDish(int id){
        //check if the dish exists, then return positive, otherwise error
        if(dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    //update a dish
    public void updateDish(Dish dish){
        dishRepository.save(dish);
    }

    //get dish by id
    public Optional<Dish> getDishById(int id){
        return dishRepository.findById(id);
    }
}
