package com.example.lose2gainmanagement.clients;

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
    private boolean swipeBack = false;
    private ItemTouchHelper itemTouchhelper;
    private SwipeController   swipeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


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

        clientViewModel.getAllClients().observe(ClientActivity.this, clientEntities -> {
            itemsList = clientEntities;
            adapter.setItemList(itemsList);
        });





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

}
