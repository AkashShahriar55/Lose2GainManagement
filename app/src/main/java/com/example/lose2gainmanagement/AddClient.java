package com.example.lose2gainmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lose2gainmanagement.ui.form.ClientImage;
import com.example.lose2gainmanagement.ui.form.FormPagerAdapter;
import com.example.lose2gainmanagement.ui.form.MeasurementFragment;
import com.example.lose2gainmanagement.ui.form.MedicalProblemFragment;
import com.example.lose2gainmanagement.ui.form.PersonalInfoFragment;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientViewModel;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddClient extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, ActivityCompat.OnRequestPermissionsResultCallback{

   private Button next_btn;
    private Button previous_btn;
    private int position;
    private FormPagerAdapter adapter;
    private ViewPager formPager;

    private ClientViewModel viewModel;

    private PersonalInfoFragment personalInfoFragment;
    private MeasurementFragment measuremntFragment;
    private MedicalProblemFragment medicalProblemFragment;
    private ClientImage clientImage;
    private  ClientEntity client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        formPager = findViewById(R.id.formPager);
        formPager.setOffscreenPageLimit(3);
        TabLayout form_indicator = findViewById(R.id.form_indicator);

        next_btn = findViewById(R.id.next_btn);
        previous_btn = findViewById(R.id.previous_btn);

        viewModel = new ViewModelProvider(AddClient.this).get(ClientViewModel.class);
        String null_str = "";
        client= new ClientEntity(null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str,null_str);

        personalInfoFragment =new PersonalInfoFragment(AddClient.this,client);
        measuremntFragment = new MeasurementFragment(client);
        medicalProblemFragment = new MedicalProblemFragment(AddClient.this,client);
        clientImage = new ClientImage(AddClient.this,client,viewModel);

        List<Fragment> list = new ArrayList<>();
        list.add(personalInfoFragment);
        list.add(measuremntFragment);
        list.add(medicalProblemFragment);
        list.add(clientImage);

         adapter = new FormPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, AddClient.this, list);
        formPager.setAdapter(adapter);
        form_indicator.setupWithViewPager(formPager);



        next_btn.setOnClickListener(view -> {
           // Log.d("currentItem",Integer.toString(formPager.getCurrentItem()));
            if (formPager.getCurrentItem() == 0){
                if (personalInfoFragment.confirmPersonalInfo()){
                    formPager.setCurrentItem(formPager.getCurrentItem() + 1,true);
                }
            }

            else if (formPager.getCurrentItem() == 1){
                if (measuremntFragment.confirmMeasurement()){
                    formPager.setCurrentItem(formPager.getCurrentItem() + 1,true);
                }
            }

            else if (formPager.getCurrentItem() == 2){
                try {
                    if (medicalProblemFragment.confirmMedicalProblem()){
                        formPager.setCurrentItem(formPager.getCurrentItem() + 1,true);

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        });

        previous_btn.setOnClickListener(view -> {
            formPager.setCurrentItem(formPager.getCurrentItem() - 1,true);
        });

        pageChange();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        clientImage.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void pageChange() {

        formPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if(position == 0){
                    previous_btn.setVisibility(View.INVISIBLE);
                    next_btn.setVisibility(View.VISIBLE);
                }
                else if (position == adapter.getCount() - 1){
                    next_btn.setVisibility(View.INVISIBLE);
                    previous_btn.setVisibility(View.VISIBLE);
                    clientImage.chageAvatar();
                }
                else{
                    next_btn.setVisibility(View.VISIBLE);
                    previous_btn.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        //String currentDate = DateFormat.getDateInstance().format(c.getTime());
        /* birth=currentDate; */
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        //Date startDate = c.getTime();
        String formattedDate = df.format(c.getTime());
        medicalProblemFragment.selectDate(formattedDate);

        medicalProblemFragment.setC(c);

        medicalProblemFragment.setCalendar_flag(1);


        //System.out.println("Current time => " + c);
        //SimpleDateFormat df_curent = new SimpleDateFormat("dd-MMM-yyyy");
        //String currentDate = df.format(endDate);





    }


}
