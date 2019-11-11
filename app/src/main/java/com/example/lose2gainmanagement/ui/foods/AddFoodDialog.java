package com.example.lose2gainmanagement.ui.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lose2gainmanagement.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddFoodDialog extends Dialog {

    private TextInputLayout fNameInput,fAmountInput,fUnitInput,fCalloriesInput,fFatInput,fProtenInput,fCrabInput;
    private Button fAddButton;
    private addFood addFood;

    public AddFoodDialog(@NonNull Context context,addFood addFood) {
        super(context);
        this.addFood = addFood;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_dialog);

        fNameInput = findViewById(R.id.fNameInput);
        fAmountInput = findViewById(R.id.fAmountInput);
        fUnitInput = findViewById(R.id.fUnitInput);
        fCalloriesInput = findViewById(R.id.fCalloriesInput);
        fFatInput = findViewById(R.id.fFatInput);
        fProtenInput = findViewById(R.id.fProtenInput);
        fCrabInput = findViewById(R.id.fCrabInput);

        fAddButton = findViewById(R.id.fAddButton);

        fAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "",amount = "",unit = "",callory= "",fat="",proten="",crab="";
                if(!fNameInput.getEditText().getText().toString().trim().isEmpty()){
                    name = fNameInput.getEditText().getText().toString();
                }else{
                    fNameInput.getEditText().setError("This field must not empty");
                    fNameInput.getEditText().requestFocus();
                }
                if(!fAmountInput.getEditText().getText().toString().trim().isEmpty()){
                    amount = fAmountInput.getEditText().getText().toString();
                }else{
                    fAmountInput.getEditText().setError("This field must not empty");
                    fAmountInput.getEditText().requestFocus();
                }
                if(!fUnitInput.getEditText().getText().toString().trim().isEmpty()){
                    unit = fUnitInput.getEditText().getText().toString();
                }else{
                    fUnitInput.getEditText().setError("This field must not empty");
                    fUnitInput.getEditText().requestFocus();
                }
                if(!fCalloriesInput.getEditText().getText().toString().trim().isEmpty()){
                    callory = fCalloriesInput.getEditText().getText().toString();
                }else{
                    fCalloriesInput.getEditText().setError("This field must not empty");
                    fCalloriesInput.getEditText().requestFocus();
                }
                if(!fFatInput.getEditText().getText().toString().trim().isEmpty()){
                    fat = fFatInput.getEditText().getText().toString();
                }else{
                    fFatInput.getEditText().setError("This field must not empty");
                    fFatInput.getEditText().requestFocus();
                }
                if(!fProtenInput.getEditText().getText().toString().trim().isEmpty()){
                    proten = fProtenInput.getEditText().getText().toString();
                }else{
                    fProtenInput.getEditText().setError("This field must not empty");
                    fProtenInput.getEditText().requestFocus();
                }
                if(!fCrabInput.getEditText().getText().toString().trim().isEmpty()){
                    crab = fCrabInput.getEditText().getText().toString();
                }else{
                    fCrabInput.getEditText().setError("This field must not empty");
                    fCrabInput.getEditText().requestFocus();
                }

                addFood.addFood(name,amount,unit,callory,fat,proten,crab);
            }
        });


    }


    public interface addFood {
        // you can define any parameter as per your requirement
        public void addFood(String fName,String fAmount,String fUnit,String fCalorie,String fFat,String fProtein,String fCrab);
    }
}
