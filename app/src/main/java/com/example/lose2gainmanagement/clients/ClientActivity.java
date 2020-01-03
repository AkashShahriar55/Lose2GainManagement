package com.example.lose2gainmanagement.clients;

<<<<<<< Updated upstream
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.lose2gainmanagement.AddClient;
import com.example.lose2gainmanagement.R;
=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.foods.FoodAdapter;
import com.example.lose2gainmanagement.ui.foods.FoodItems;
import com.example.lose2gainmanagement.ui.foods.FoodViewModel;
>>>>>>> Stashed changes
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity {

    private ClientViewModel clientViewModel;
    private RecyclerView recyclerView;
    private List<ClientEntity> itemsList = new ArrayList<>();
    private FloatingActionButton fab;
    private ClientAdapter adapter;
    private SearchView searchClient;
<<<<<<< Updated upstream
    private boolean swipeBack = false;
    private ItemTouchHelper itemTouchhelper;
    private SwipeController   swipeController;
=======
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

<<<<<<< Updated upstream

        fab = findViewById(R.id.clientAddFab);
        searchClient = findViewById(R.id.clientSearch);
        recyclerView = findViewById(R.id.clientRv);
        swipeController = new SwipeController();
        itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
        clientViewModel = new ViewModelProvider(ClientActivity.this).get(ClientViewModel.class);
        adapter = new ClientAdapter(itemsList,ClientActivity.this, swipeController,clientViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ClientActivity.this));

=======
        fab = findViewById(R.id.clientAddFab);
        searchClient = findViewById(R.id.clientSearch);
        recyclerView = findViewById(R.id.clientRv);
        adapter = new ClientAdapter(itemsList,ClientActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ClientActivity.this));
        clientViewModel = new ViewModelProvider(ClientActivity.this).get(ClientViewModel.class);
>>>>>>> Stashed changes
        clientViewModel.getAllClients().observe(ClientActivity.this, clientEntities -> {
            itemsList = clientEntities;
            adapter.setItemList(itemsList);
        });
<<<<<<< Updated upstream





        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ClientActivity.this, AddClient.class);
            startActivity(intent);
        });



    }

    public void attach(){
        swipeController = new SwipeController();
        itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

=======
    }
>>>>>>> Stashed changes
}
