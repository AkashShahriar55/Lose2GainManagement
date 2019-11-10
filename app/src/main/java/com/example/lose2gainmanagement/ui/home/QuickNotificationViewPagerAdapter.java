package com.example.lose2gainmanagement.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.lose2gainmanagement.R;

import java.util.List;

public class QuickNotificationViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<QuickNotificationInfo> quickNotificationInfoList;

    public QuickNotificationViewPagerAdapter(Context context, List<QuickNotificationInfo> quickNotificationInfoList) {
        this.context = context;
        this.quickNotificationInfoList = quickNotificationInfoList;
    }

    @Override
    public int getCount() {
        return quickNotificationInfoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView((View) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.quick_notification,null);

        TextView quick_clint_id,quick_clint_name,quick_follow_text;

        quick_clint_id = v.findViewById(R.id.quick_clint_id);
        quick_clint_name = v.findViewById(R.id.quick_clint_name);
        quick_follow_text = v.findViewById(R.id.quick_follow_text);

        Button check_btn = v.findViewById(R.id.check_btn);
        YoYo.with(Techniques.Pulse).duration(2000).repeat(YoYo.INFINITE).playOn(check_btn);

        quick_clint_id.setText(quickNotificationInfoList.get(position).getClint_id());
        quick_clint_name.setText(quickNotificationInfoList.get(position).getClinet_name());
        quick_follow_text.setText(quickNotificationInfoList.get(position).getLast_follow_up_date());
        container.addView(v);
        return v;
    }
}
