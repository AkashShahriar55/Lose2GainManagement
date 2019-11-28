package com.example.lose2gainmanagement.ui.DietChart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.lose2gainmanagement.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DietChartFragment extends Fragment implements StepOneFragment.StepOneNext, StepTwoFragment.StepTwoNext, StepThreeFragment.StepThreeFinish {

    private DietChartStepAdapter adapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<DietChartBela> belaItems = new ArrayList<>();
    private CustomViewPager viewPager;
    private TabLayout indicator;
    private TextView next,previous,steps;
    private DietChart dietChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diet_chart, container, false);

        viewPager = root.findViewById(R.id.DietChartViewPager);
        indicator = root.findViewById(R.id.DietChartStepIndicator);
        next = root.findViewById(R.id.DietChartNextButton);
        previous = root.findViewById(R.id.DietChartPreviousButton);
        steps = root.findViewById(R.id.DietChartStepTitle);

        dietChart = new DietChart("","","","","","","","","",belaItems);


        viewPager.setPagingEnabled(false);


        fragmentList.add(new StepOneFragment(dietChart,this));
        fragmentList.add(new StepTwoFragment(belaItems,this));
        fragmentList.add(new StepThreeFragment(dietChart,this));

        adapter = new DietChartStepAdapter(getParentFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,getContext(),fragmentList);
        viewPager.setAdapter(adapter);
        indicator.setupWithViewPager(viewPager);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == adapter.getCount()-1){
                    next.setVisibility(View.INVISIBLE);
                }else{
                    next.setVisibility(View.VISIBLE);
                }
                if(position == 0){
                    previous.setVisibility(View.INVISIBLE);
                }else{
                    previous.setVisibility(View.VISIBLE);
                }

                steps.setText("Step "+(position+1));

                Toast.makeText(getContext()," check: "+belaItems.size(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager.getCurrentItem();
                if(position == 0){
                    previous.setVisibility(View.VISIBLE);
                }
                viewPager.setCurrentItem(position+1);
                if(position+1 == adapter.getCount()-1){
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager.getCurrentItem();
                if(position == adapter.getCount()-1){
                    next.setVisibility(View.VISIBLE);
                }
                viewPager.setCurrentItem(position-1);
                if(position-1 == 0){
                    previous.setVisibility(View.INVISIBLE);
                }
            }
        });


        return root;
    }

    @Override
    public void next() {
        int position = viewPager.getCurrentItem();
        if(position == 0){
            previous.setVisibility(View.VISIBLE);
        }
        viewPager.setCurrentItem(position+1);
        if(position+1 == adapter.getCount()-1){
            next.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent(getContext(), DietChartPrinting.class);
        intent.putExtra("chart",dietChart);
        this.startActivity(intent);
    }

}