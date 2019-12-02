package com.example.lose2gainmanagement.ui.form.clientDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ClientDao {


    @Insert
    void insert(ClientEntity clientEntity);

    @Query("SELECT * FROM clients ORDER BY priority DESC")
    LiveData<List<ClientEntity>> getAllClients();
}
