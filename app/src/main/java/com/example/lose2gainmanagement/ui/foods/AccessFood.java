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
    public void insertFood(FoodItems ... items);
    @Update
    public void updateFood(FoodItems ... items);
    @Delete
    public void deleteFood(FoodItems ... items);
    @Query("SELECT * FROM FoodItems")
    public LiveData<List<FoodItems>> loadAllFoodItems();

}
