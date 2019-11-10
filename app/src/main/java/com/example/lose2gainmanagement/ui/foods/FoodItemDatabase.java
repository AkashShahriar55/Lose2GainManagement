package com.example.lose2gainmanagement.ui.foods;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodItems.class}, version = 1)
public abstract class FoodItemDatabase extends RoomDatabase {
    public abstract AccessFood accessFood();

    private static volatile FoodItemDatabase INSTANCE;

    static FoodItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoodItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodItemDatabase.class, "food_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
