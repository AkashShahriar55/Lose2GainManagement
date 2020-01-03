package com.example.lose2gainmanagement.ui.form;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lose2gainmanagement.AddClient;
import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MedicalProblemFragment extends Fragment{


    private TextInputLayout input_client_added_date, edt_food_preferred, edt_food_allergic, edt_food_fav;
    private String food_preferred,food_allergic,food_fav,packages,client_added_date;
    private RadioButton radioButton_1,radioButton_2;
    private Calendar c;

    private int calendar_flag = 0;


    private Context context;
    private ClientEntity client;

    public MedicalProblemFragment() {
    }

    public MedicalProblemFragment(Context context, ClientEntity client) {
        this.context = context;
        this.client = client;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.form_medical_problem,container,false);

        input_client_added_date = rootView.findViewById(R.id.input_client_added_date);

        radioButton_1 = rootView.findViewById(R.id.package_12);
        radioButton_2 = rootView.findViewById(R.id.package_24);


        Objects.requireNonNull(input_client_added_date.getEditText()).setOnFocusChangeListener((view, hasFocused) -> {

            if (hasFocused){
                DateDialog dateDialog = new DateDialog();

                dateDialog.show(getChildFragmentManager(),"DatePicker");
            }
        });


        input_client_added_date.getEditText().setOnClickListener(view -> {
            DateDialog dateDialog = new DateDialog();

            dateDialog.show(getChildFragmentManager(),"DatePicker");
        });

        //editText Initialization
        edt_food_preferred = rootView.findViewById(R.id.edt_food_preferred);
        edt_food_allergic = rootView.findViewById(R.id.edt_food_allergic);
        edt_food_fav = rootView.findViewById(R.id.edt_food_fav);

        //setting current date as default

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        input_client_added_date.getEditText().setText(formattedDate);

        return rootView;
    }



    public void selectDate(String date){
        //Toast.makeText(context,"hhh",Toast.LENGTH_SHORT).show();
        input_client_added_date.getEditText().setText(date);
    }

    private boolean Validate(){
        food_preferred = edt_food_preferred.getEditText().getText().toString().trim();
        food_allergic = edt_food_allergic.getEditText().getText().toString().trim();
        food_fav = edt_food_fav.getEditText().getText().toString().trim();
        client_added_date = input_client_added_date.getEditText().getText().toString().trim();

        if(radioButton_1.isChecked()){
            packages = "12";
        }
        else if(radioButton_2.isChecked()){
            packages = "24";
        }

        Log.d("package",packages);


        edt_food_preferred.setErrorEnabled(false);
        edt_food_preferred.setError(null);
        edt_food_allergic.setErrorEnabled(false);
        edt_food_allergic.setError(null);
        edt_food_fav.setErrorEnabled(false);
        edt_food_fav.setError(null);

        if (food_preferred.isEmpty()){
            edt_food_preferred.setErrorEnabled(true);
            edt_food_preferred.setError("Field Can't be empty");
            return false;
        }

        else if (food_allergic.isEmpty()){
            edt_food_allergic.setErrorEnabled(true);
            edt_food_allergic.setError("Field Can't be empty");
            return false;
        }

        else if (food_fav.isEmpty()){
            edt_food_fav.setErrorEnabled(true);
            edt_food_fav.setError("Field Can't be empty");
            return false;
        }

        else {
            return true;
        }

    }


    public void setC(Calendar c) {
        this.c = c;
    }


    public void setCalendar_flag(int calendar_flag) {
        this.calendar_flag = calendar_flag;
    }

    public boolean confirmMedicalProblem() throws ParseException {
        if (Validate()){


            client.setFood_preferred(food_preferred);
            client.setFood_allergic(food_allergic);
            client.setFood_fav(food_fav);
            client.setClient_added_date(client_added_date);
            client.setLast_followup(client_added_date);
            client.setPackages(packages);

            //for followup and running week
            SimpleDateFormat sdf =new SimpleDateFormat("dd-MMM-yyyy");
            Date startDate = sdf.parse(client_added_date);
            Date endDate = Calendar.getInstance().getTime();

            long diff = endDate.getTime() - startDate.getTime();
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            long running_week = days/7;
            String runningWeek = Long.toString(running_week);

            Log.d("runningWeek",runningWeek);


            Calendar next_followup_calendar = Calendar.getInstance();
            if(calendar_flag == 1){
                 next_followup_calendar = c;
            }
            next_followup_calendar.add(Calendar.DATE, (int) ((running_week*7)+7));

            String next_followUp_date = sdf.format(next_followup_calendar.getTime());
            client.setRunning_week(runningWeek);
            client.setNext_followup(next_followUp_date);

            Log.d("next_followUp_date",next_followUp_date);

            return true;
        }
        else{
            return false;
        }
    }
}
