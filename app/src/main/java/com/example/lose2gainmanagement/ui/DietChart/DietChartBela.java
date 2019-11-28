package com.example.lose2gainmanagement.ui.DietChart;

import java.io.Serializable;
import java.util.List;

public class DietChartBela implements Serializable {
    private String bela;
    private List<DietChartFood> foodItems;

    public DietChartBela(String bela, List<DietChartFood> foodItems) {
        this.bela = bela;
        this.foodItems = foodItems;
    }

    public String getBela() {
        return bela;
    }

    public void setBela(String bela) {
        this.bela = bela;
    }

    public List<DietChartFood> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<DietChartFood> foodItems) {
        this.foodItems = foodItems;
    }
}
