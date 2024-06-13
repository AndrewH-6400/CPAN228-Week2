package com.humber.Week6JPA.services;

import com.humber.Week6JPA.models.Dish;
import com.humber.Week6JPA.repositories.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {


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

    //pagination and sorting method
    public Page<Dish> getPaginatedDishes(int pageSize, int pageNo, String sortField, String sortDirection){
        //sort the data based on the sort field and sort direction
        //conditional sort the field by asc or desc based on sortDirection
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        //Sending the pageable with all the layout information and returning a Page object of Dishes
        Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
        return dishRepository.findAll(pageable);
    }
}
