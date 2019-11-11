package com.example.lose2gainmanagement.ui.foods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lose2gainmanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment implements AddFoodDialog.addFood{

    private FoodViewModel foodViewModel;
    private RecyclerView recyclerView;
    private List<FoodItems> itemsList = new ArrayList<>();
    private FloatingActionButton fab;
    private FoodAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);

        fab = root.findViewById(R.id.FoodAddFab);

        recyclerView = root.findViewById(R.id.FoodRv);
        adapter = new FoodAdapter(itemsList,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        foodViewModel.getAllWords().observe(this, new Observer<List<FoodItems>>() {
            @Override
            public void onChanged(@Nullable final List<FoodItems> items) {
                // Update the cached copy of the words in the adapter.
                itemsList = items;
                adapter.setItemList(itemsList);

                /*for(FoodItems i:items){
                    Toast.makeText(getContext(),""+i.getfId()+i.getfName(),Toast.LENGTH_SHORT).show();
                }*/
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FoodItems item = new FoodItems("apple",10,"kg",1.0,1.0,1.0,1.0);
                //foodViewModel.insert(item);
                new AddFoodDialog(getContext(), FoodFragment.this).show();
            }
        });

        return root;
    }

    @Override
    public void addFood(String fName, String fAmount, String fUnit, String fCalorie, String fFat, String fProtein, String fCrab) {

        FoodItems item = new FoodItems(fName,fAmount,fUnit,fCalorie,fFat,fProtein,fCrab);
        foodViewModel.insert(item);
    }
}