package com.example.lose2gainmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lose2gainmanagement.ui.form.ClientImage;
import com.example.lose2gainmanagement.ui.form.FormPagerAdapter;
import com.example.lose2gainmanagement.ui.form.MeasuremntFragment;
import com.example.lose2gainmanagement.ui.form.MedicalProblemFragment;
import com.example.lose2gainmanagement.ui.form.PersonalInfoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddClient extends AppCompatActivity {

   private Button next_btn;
    private Button previous_btn;
    private int position;
    private FormPagerAdapter adapter;
    private ViewPager formPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        formPager = findViewById(R.id.formPager);
        TabLayout form_indicator = findViewById(R.id.form_indicator);

        next_btn = findViewById(R.id.next_btn);
        previous_btn = findViewById(R.id.previous_btn);

        List<Fragment> list = new ArrayList<>();
        list.add(new PersonalInfoFragment());
        list.add(new MeasuremntFragment());
        list.add(new MedicalProblemFragment());
        list.add(new ClientImage(AddClient.this));

         adapter = new FormPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, AddClient.this, list);
        formPager.setAdapter(adapter);
        form_indicator.setupWithViewPager(formPager);

        next_btn.setOnClickListener(view -> formPager.setCurrentItem(formPager.getCurrentItem() + 1,true));

        previous_btn.setOnClickListener(view -> formPager.setCurrentItem(formPager.getCurrentItem() - 1,true));

        pageChange();
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

}
