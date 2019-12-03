package com.example.lose2gainmanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lose2gainmanagement.AddClient;
import com.example.lose2gainmanagement.LandingPage;
import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.clients.ClientActivity;
import com.example.lose2gainmanagement.ui.DietChart.DietChartPrinting;
import com.example.lose2gainmanagement.ui.foods.FoodFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewPager quick_viewpager;
    QuickNotificationViewPagerAdapter adapter;
    TabLayout tab_indicator;
    navigateTo myListener;

    static boolean animFlag = false;


    LinearLayout home_first_linear,dietChart,home_client_database,foodDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        /* set up viewPager for quickNotification **/
        quick_viewpager = root.findViewById(R.id.quick_viewpager);

        tab_indicator = root.findViewById(R.id.tab_indicator);
        dietChart = root.findViewById(R.id.Diet_chart_layout);
        foodDatabase = root.findViewById(R.id.home_food_database);

        LinearLayout notification_section = root.findViewById(R.id.linear4);
        ScrollView dashboard_scrollview = root.findViewById(R.id.dashboard_scrollview);
        dashboard_scrollview.setVerticalScrollBarEnabled(false);
        //dashboard_scrollview.setHorizontalScrollBarEnabled(false);

        myListener = (navigateTo) getActivity();



        //layout button for add client
        home_first_linear = root.findViewById(R.id.home_first_linear);

        //Layout Button for client database
        home_client_database = root.findViewById(R.id.home_client_database);



        List<QuickNotificationInfo> infoList = new ArrayList<>();

        infoList.add(new QuickNotificationInfo("101","Bishal", "29/08/2018"));
        infoList.add(new QuickNotificationInfo("202","Akash", "25/03/2017"));
        infoList.add(new QuickNotificationInfo("303","XYZ", "29/02/2018"));
        infoList.add(new QuickNotificationInfo("404","Sady", "29/02/2018"));
        infoList.add(new QuickNotificationInfo("303","Abir", "29/02/2018"));

        adapter = new QuickNotificationViewPagerAdapter(getContext(),infoList);
        quick_viewpager.setAdapter(adapter);

        /*Set up tab indicator with view pager*/
        tab_indicator.setupWithViewPager(quick_viewpager);

        if(animFlag == false){
            Animation fade_in,slide_in;
            //YoYo.with(Techniques.DropOut).duration(3000).playOn(notification_section);

            fade_in = AnimationUtils.loadAnimation(getContext(),
                    R.anim.fade_in);

            notification_section.setAnimation(fade_in);


            slide_in = AnimationUtils.loadAnimation(getContext(),
                    R.anim.slide_in_bottom);
            dashboard_scrollview.setAnimation(slide_in);
            animFlag = true;
        }




        home_first_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddClient.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });



        dietChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.navigateToDietChart();
            }
        });

        foodDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.navigateToFood();
            }
        });


        home_client_database.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ClientActivity.class);
            getContext().startActivity ( intent );
        });


        return root;
    }

    public interface navigateTo{
        void navigateToFood();
        void navigateToDietChart();
    }


}