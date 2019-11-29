package com.example.lose2gainmanagement.ui.DietChart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

public class DietChartStepAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private List<Fragment> fragments;
    private int position;

    public DietChartStepAdapter(@NonNull FragmentManager fm, int behavior,Context context,List<Fragment> fragments) {
        super(fm, behavior);
        this.context = context;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        this.position = position;
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
