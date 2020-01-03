package com.example.lose2gainmanagement.clients;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lose2gainmanagement.AddClient;
import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.FormPagerAdapter;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClientDetailsActivity extends AppCompatActivity{

    private Button next_btn;
    private Button previous_btn;

    private FormPagerAdapter adapter;
    private ViewPager detailsPager;

    private boolean update_flag =false;
    private String val;

    private ClientEntity client;
    private ImageView details_img;
    private DetailsMedicalProblemFragment detailsMedicalProblemFragment;
    /*private ClientViewModel viewModel;*/



    @SuppressLint({"ClickableViewAccessibility", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        if(savedInstanceState==null){
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            client = (ClientEntity) extras.getSerializable("client");

        }

        /*viewModel = new ViewModelProvider(ClientDetailsActivity.this).get(ClientViewModel.class);*/

        detailsPager = findViewById(R.id.detailsPager);
        //detailsPager.setOffscreenPageLimit(2);
        TabLayout details_indicator = findViewById(R.id.details_indicator);

        next_btn = findViewById(R.id.details_next_btn);
        previous_btn = findViewById(R.id.details_previous_btn);
        details_img = findViewById(R.id.details_img);


        Bitmap bitmap = loadImageFromStorage(client.getClient_image_directory(),client.getClient_image());
        details_img.setImageBitmap(bitmap);


        DetailsPersonalInfoFragment detailsPersonalInfoFragment = new DetailsPersonalInfoFragment(ClientDetailsActivity.this, client);
        DetailsMeasurementFragment detailsMeasurementFragment = new DetailsMeasurementFragment(ClientDetailsActivity.this, client);

        detailsMedicalProblemFragment = new DetailsMedicalProblemFragment(ClientDetailsActivity.this, client);


        List<Fragment> lst = new ArrayList<>();
        lst.add(detailsPersonalInfoFragment);
        lst.add(detailsMeasurementFragment);
        lst.add(detailsMedicalProblemFragment);

        adapter = new FormPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, ClientDetailsActivity.this, lst);
        detailsPager.setAdapter(adapter);
        details_indicator.setupWithViewPager(detailsPager);



        previous_btn.setOnClickListener(view -> {
            detailsPager.setCurrentItem(detailsPager.getCurrentItem() - 1,true);
        });

        next_btn.setOnClickListener(view -> {

            detailsPager.setCurrentItem(detailsPager.getCurrentItem() + 1,true);
        });




        LinearLayout tabStrip = ((LinearLayout)details_indicator.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener((v, event) -> true);
        }





        pageChange();
    }


    private void pageChange() {

        detailsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    private Bitmap loadImageFromStorage(String path, String img_name)
    {
        Bitmap b = null;
        try {
            File f=new File(path, img_name);
            b= BitmapFactory.decodeStream(new FileInputStream(f));
            //ImageView img=(ImageView)findViewById(R.id.imgPicker);
            //img.setImageBitmap(b);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return b;

    }


}
