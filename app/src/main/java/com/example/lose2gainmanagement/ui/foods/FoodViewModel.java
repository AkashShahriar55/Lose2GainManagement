package com.example.lose2gainmanagement.ui.foods;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository mRepository;

    private LiveData<List<FoodItems>> mAllFoods;

    public FoodViewModel (Application application) {
        super(application);
        mRepository = new FoodRepository(application);
        mAllFoods = mRepository.getmAllFoods();

    }

    public LiveData<List<FoodItems>> getAllWords() { return mAllFoods; }

    public void insert(FoodItems foods) { mRepository.insert(foods); }
}
