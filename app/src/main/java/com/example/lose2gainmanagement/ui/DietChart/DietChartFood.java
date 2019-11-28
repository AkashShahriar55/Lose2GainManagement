package com.example.lose2gainmanagement.ui.DietChart;

import com.example.lose2gainmanagement.ui.foods.FoodItems;

import java.io.Serializable;

public class DietChartFood implements Serializable {
    private String Amount;
    private FoodItems food;

    public DietChartFood(String amount, FoodItems food) {
        Amount = amount;
        this.food = food;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public FoodItems getFood() {
        return food;
    }

    public void setFood(FoodItems food) {
        this.food = food;
    }
}
