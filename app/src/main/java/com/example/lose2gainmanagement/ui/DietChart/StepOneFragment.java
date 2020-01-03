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
import android.widget.Spinner;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.DateDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StepOneFragment extends Fragment {

    private DietChart chart;
    TextInputLayout cName,cWeight,cHeight,cAge,cMedicalProblem,chartName,chartDate;
    Button next;
    StepOneNext stepOneNext;
    Spinner cSex;

    private Calendar c;

    public StepOneFragment(DietChart chart,StepOneNext stepOneNext) {
        this.chart = chart;
        this.stepOneNext = stepOneNext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_step_one_fragment, container, false);


        cName = root.findViewById(R.id.DCClientName);
        cWeight = root.findViewById(R.id.DCClientWeight);
        cHeight = root.findViewById(R.id.DCClientHeight);
        cAge = root.findViewById(R.id.DCClientAge);
        cSex = root.findViewById(R.id.DCClientSex);
        cMedicalProblem = root.findViewById(R.id.DCClientMedicalProblem);
        next = root.findViewById(R.id.DietChartStepOneNext);
        chartName = root.findViewById(R.id.DCChartName);
        chartDate = root.findViewById(R.id.DCChartDate);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name,Weight,Height,Age,Sex,MedicalProblem,chrtName,cDate;
                boolean hasError = false;
                Name = cName.getEditText().getText().toString();
                Weight = cWeight.getEditText().getText().toString();
                Height = cHeight.getEditText().getText().toString();
                Age = cAge.getEditText().getText().toString();
                Sex = (String) cSex.getSelectedItem();
                MedicalProblem = cMedicalProblem.getEditText().getText().toString();
                chrtName = chartName.getEditText().getText().toString();
                cDate = chartDate.getEditText().getText().toString();

                if(Name.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }
                if(Weight.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }
                if(Height.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }
                if(Height.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }
                if(Age.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }
                if(Sex.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }
                if(MedicalProblem.trim().isEmpty()){
                    cName.setError("This field mustn't empty");
                    cName.requestFocus();
                    hasError = true;
                }

                if(chrtName.trim().isEmpty()){
                    chartName.setError("This field mustn't empty");
                    chartName.requestFocus();
                    hasError = true;
                }

                if(cDate.trim().isEmpty()){
                    chartDate.setError("This field mustn't empty");
                    chartDate.requestFocus();
                    hasError = true;
                }

                if(!hasError){
                    chart.setcName(Name);
                    chart.setcWeight(Weight);
                    chart.setcHeight(Height);
                    chart.setcAge(Age);
                    chart.setcSex(Sex);
                    chart.setcMedicalProblem(MedicalProblem);
                    chart.setChartDate(cDate);
                    chart.setChartName(chrtName);
                    stepOneNext.next();
                }

            }
        });



        return root;



    }

    public interface StepOneNext{
        public void next();
    }
}
