package com.example.lose2gainmanagement.ui.DietChart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lose2gainmanagement.R;
import com.google.android.material.textfield.TextInputLayout;

public class StepThreeFragment extends Fragment {

    EditText instruction;
    Button finish;
    StepThreeFinish stepThreeFinish;
    DietChart chart;

    public StepThreeFragment(DietChart chart,StepThreeFinish stepThreeFinish) {
        this.chart = chart;
        this.stepThreeFinish = stepThreeFinish;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_step_three_fragment, container, false);


        instruction = root.findViewById(R.id.DCInstruction);
        finish = root.findViewById(R.id.DietChartStepThreeFinish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String instructionsText = instruction.getText().toString();
                if(instructionsText.trim().isEmpty()){
                    instruction.setError("This Field Mustn't empty");
                    instruction.requestFocus();
                }
                else{
                    chart.setInstruction(instructionsText);
                    stepThreeFinish.finish();
                }
            }
        });

        return root;
    }


    public interface StepThreeFinish{
        void finish();
    }
}
