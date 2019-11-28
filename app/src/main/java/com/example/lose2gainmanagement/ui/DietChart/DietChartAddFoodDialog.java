package com.example.lose2gainmanagement.ui.DietChart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.foods.FoodItems;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class DietChartAddFoodDialog extends Dialog {


    private List<FoodItems> foodItemsList;
    private ArrayList<String> foodNames = new ArrayList<>();
    private Spinner spinnerFood;
    private Context context;
    private TextInputLayout inputLayout;
    private Button addFoodButton;
    private sendFood food;
    private int position;

    public DietChartAddFoodDialog(@NonNull Context context,List<FoodItems> foodItems,sendFood sendFood,int position) {
        super(context);
        this.context = context;
        this.foodItemsList = foodItems;
        this.food = sendFood;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_food);

        spinnerFood = findViewById(R.id.DietChartAddFoodName);
        inputLayout = findViewById(R.id.DietChartAddFoodQuantity);
        addFoodButton = findViewById(R.id.DietChartAddFoodButton);

        for(FoodItems f:foodItemsList){
            foodNames.add(f.getfName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item,foodNames);
        spinnerFood.setAdapter(adapter);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = inputLayout.getEditText().getText().toString();
                if(amount.trim().isEmpty()){
                    inputLayout.setError("This field mustn't empty");
                    inputLayout.requestFocus();
                }else{
                    DietChartFood foodItem = new DietChartFood(amount,foodItemsList.get(spinnerFood.getSelectedItemPosition()));
                    food.sendFoodName(foodItem,position);
                    dismiss();
                }
            }
        });


    }

    public interface sendFood{
        public void sendFoodName(DietChartFood food,int position);
    }
}
