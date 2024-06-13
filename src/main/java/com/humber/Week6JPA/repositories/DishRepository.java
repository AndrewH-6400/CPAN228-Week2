package com.humber.Week6JPA.repositories;


import com.humber.Week6JPA.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DishRepository extends JpaRepository<Dish,Integer> {
    //Jpa can recognise through the var passed and name of function what you are looking for and accomodate that
    //Jpa can even work if you just add ignoreCase
    //List<Dish> findByCategoryAndPrice(String category, Double price);

    //native query (custom method) changes based on what you use (SQL, mongoDB, etc...)
    @Query(value = "SELECT * FROM dish WHERE LOWER(category) = LOWER(?1) and price = ?2", nativeQuery = true)
    List<Dish> findByCategoryAndPrice(String category, Double price);

}
