package com.example.lose2gainmanagement.ui.DietChart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lose2gainmanagement.R;

import java.util.List;

public class DietChartBelaFoodAdapter extends RecyclerView.Adapter<DietChartBelaFoodAdapter.FoodViewHolder> {

    private List<DietChartFood> itemList;
    private Context context;

    public DietChartBelaFoodAdapter(List<DietChartFood> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public DietChartBelaFoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_food,parent,false);
        return new DietChartBelaFoodAdapter.FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DietChartBelaFoodAdapter.FoodViewHolder holder, int position) {
        final DietChartFood item = itemList.get(position);

        holder.foodName.setText(item.getFood().getfName());
        holder.foodQuantity.setText(item.getAmount());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,foodQuantity;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.DietChartBelaFoodName);
            foodQuantity = itemView.findViewById(R.id.DietChartBelaFoodQuantity);


        }
    }

    public List<DietChartFood> getItemList() {
        return itemList;
    }

    public void setItemList(List<DietChartFood> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
}
