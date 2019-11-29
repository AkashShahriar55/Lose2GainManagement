package com.example.lose2gainmanagement.ui.DietChart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.foods.FoodAdapter;
import com.example.lose2gainmanagement.ui.foods.FoodItems;
import com.example.lose2gainmanagement.ui.foods.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class DietChartBelaAdapter extends RecyclerView.Adapter<DietChartBelaAdapter.BelaViewHolder>  {

    private List<DietChartBela> itemList;
    private Context context;
    private FoodViewModel foodViewModel;
    private List<FoodItems> foodList;

    public DietChartBelaAdapter(List<DietChartBela> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;

        foodViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(FoodViewModel.class);

        foodViewModel.getAllWords().observe((LifecycleOwner) context, new Observer<List<FoodItems>>() {
            @Override
            public void onChanged(@Nullable final List<FoodItems> items) {
                // Update the cached copy of the words in the adapter.
                foodList = items;
            }
        });
    }

    @NonNull
    @Override
    public DietChartBelaAdapter.BelaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_bela,parent,false);
        return new DietChartBelaAdapter.BelaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DietChartBelaAdapter.BelaViewHolder holder, final int position)  {
        final DietChartBela item = itemList.get(position);
        holder.belaName.setText(item.getBela());
        holder.addBelaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DietChartAddFoodDialog(context,foodList,holder,position).show();
            }
        });

        holder.adapter = new DietChartBelaFoodAdapter(item.getFoodItems(),context);
        holder.recyclerView.setAdapter(holder.adapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



    public class BelaViewHolder extends RecyclerView.ViewHolder implements DietChartAddFoodDialog.sendFood{

        TextView belaName;
        Button addBelaButton;
        RecyclerView recyclerView;
        DietChartBelaFoodAdapter adapter;

        public BelaViewHolder(@NonNull View itemView) {
            super(itemView);

            belaName = itemView.findViewById(R.id.DietChartBela);
            addBelaButton = itemView.findViewById(R.id.DietChartBelaAddButton);
            recyclerView = itemView.findViewById(R.id.DietChartBelaFoods);


        }


        @Override
        public void sendFoodName(DietChartFood food,int position) {
            List<DietChartFood> foodItems  = itemList.get(position).getFoodItems();
            foodItems.add(food);
            itemList.get(position).setFoodItems(foodItems);
            //Toast.makeText(context," "+food.getAmount()+" "+food.getFood().getfName()+" "+itemList.get(position).getFoodItems().size()+" "+position,Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }

    public List<DietChartBela> getItemList() {
        return itemList;
    }

    public void setItemList(List<DietChartBela> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
}
