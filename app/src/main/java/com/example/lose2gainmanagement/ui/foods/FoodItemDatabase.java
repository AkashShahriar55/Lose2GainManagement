package com.example.lose2gainmanagement.ui.foods;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FoodItems.class}, version = 1)
public abstract class FoodItemDatabase extends RoomDatabase {
    public abstract AccessFood accessFood();

    private static volatile FoodItemDatabase INSTANCE;

    static FoodItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoodItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodItemDatabase.class, "database_food1").addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            new PopulateDbAsync(INSTANCE).execute();
                        }
                    })
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AccessFood mDao;

        PopulateDbAsync(FoodItemDatabase db) {
            mDao = db.accessFood();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            FoodItems item = new FoodItems("পনির","১০০","গ্রাম","৩০০","২০","২৩.৩","৩.৩");
            mDao.insertFood(item);
            item = new FoodItems("ডিম","১","পিস","৭২","৬","৫","০");
            mDao.insertFood(item);
            item = new FoodItems("হোয়ে প্রোটিন","৩০","গ্রাম","১২০","২৪","১","৩");
            mDao.insertFood(item);
            item = new FoodItems("মিক্সড ভেজিটেবল","১","কাপ","৪৬","৩","২","৪");
            mDao.insertFood(item);
            item = new FoodItems("চিজ স্লাইস","১","পিস","৬২","৪","৫","০.৩");
            mDao.insertFood(item);

            return null;
        }
    }
}
