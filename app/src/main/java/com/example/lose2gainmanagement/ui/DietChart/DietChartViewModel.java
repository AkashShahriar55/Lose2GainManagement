package com.example.lose2gainmanagement.ui.DietChart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DietChartViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DietChartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}