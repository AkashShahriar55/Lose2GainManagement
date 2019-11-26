package com.example.lose2gainmanagement.ui.foods;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccessFood{
    @Insert
    void insertFood(FoodItems... items);
    @Update
    void updateFood(FoodItems... items);
    @Delete
    void deleteFood(FoodItems... items);
    @Query("SELECT * FROM FoodItems")
    LiveData<List<FoodItems>> loadAllFoodItems();

}
