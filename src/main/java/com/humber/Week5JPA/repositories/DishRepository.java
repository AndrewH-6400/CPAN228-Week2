package com.humber.Week5JPA.repositories;


import com.humber.Week5JPA.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DishRepository extends JpaRepository<Dish,Integer> {

}
