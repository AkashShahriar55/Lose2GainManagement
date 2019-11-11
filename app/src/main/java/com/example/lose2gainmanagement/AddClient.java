package com.example.lose2gainmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.lose2gainmanagement.ui.form.FormPagerAdapter;
import com.example.lose2gainmanagement.ui.form.MeasuremntFragment;
import com.example.lose2gainmanagement.ui.form.MedicalProblemFragment;
import com.example.lose2gainmanagement.ui.form.PersonalInfoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        ViewPager formPager = findViewById(R.id.formPager);
        TabLayout form_indicator = findViewById(R.id.form_indicator);

        List<Fragment> list = new ArrayList<>();
        list.add(new PersonalInfoFragment());
        list.add(new MeasuremntFragment());
        list.add(new MedicalProblemFragment());

        FormPagerAdapter adapter = new FormPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, AddClient.this, list);
        formPager.setAdapter(adapter);
        form_indicator.setupWithViewPager(formPager);
    }
}
