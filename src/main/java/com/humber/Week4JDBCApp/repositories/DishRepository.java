package com.humber.Week4JDBCApp.repositories;

import com.humber.Week4JDBCApp.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@Repository
public class DishRepository {

    @Autowired
    JdbcTemplate template;

    //save a dish



    //get all dishes
    public List<Dish> getDishes(){
        String sql = "SELECT * FROM Dish";

        RowMapper<Dish> mapper = new RowMapper<Dish>() {

            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
                Dish myDish = new Dish();

                //call the attribute through either the name of the column of the index
                //the first column is ID, so we call the first column
                //rs is the row of the table we are getting results from
                myDish.setId(rs.getInt(1));
                myDish.setName(rs.getString(2));
                myDish.setCategory(rs.getString(3));
                myDish.setPrice(rs.getDouble(4));

                return myDish;
            }
        };

        //mapper loops through the query results to return a list
        List<Dish> dishes = template.query(sql, mapper);

        return dishes;
    }
}
