package com.example.lose2gainmanagement.clients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


        fab = findViewById(R.id.clientAddFab);
        searchClient = findViewById(R.id.clientSearch);
        recyclerView = findViewById(R.id.clientRv);
        adapter = new ClientAdapter(itemsList,ClientActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ClientActivity.this));
        clientViewModel = new ViewModelProvider(ClientActivity.this).get(ClientViewModel.class);
        clientViewModel.getAllClients().observe(ClientActivity.this, clientEntities -> {
            itemsList = clientEntities;
            adapter.setItemList(itemsList);
        });


        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ClientActivity.this, AddClient.class);
            startActivity(intent);
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX/4, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }
}
