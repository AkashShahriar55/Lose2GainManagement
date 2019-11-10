package com.example.lose2gainmanagement.ui.foods;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodRepository {
    private AccessFood mFoodDao;
    private LiveData<List<FoodItems>> mAllFoods;

    FoodRepository(Application application) {
        FoodItemDatabase db = FoodItemDatabase.getDatabase(application);
        mFoodDao = db.accessFood();
        mAllFoods = mFoodDao.loadAllFoodItems();
    }

    private static class insertAsyncTask extends AsyncTask<FoodItems, Void, Void> {

        private AccessFood mAsyncTaskDao;

        insertAsyncTask(AccessFood dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FoodItems... params) {
            mAsyncTaskDao.insertFood(params[0]);
            return null;
        }
    }

    LiveData<List<FoodItems>> getmAllFoods() {
        return mAllFoods;
    }


    public void insert (FoodItems word) {
        new insertAsyncTask(mFoodDao).execute(word);
    }
}
