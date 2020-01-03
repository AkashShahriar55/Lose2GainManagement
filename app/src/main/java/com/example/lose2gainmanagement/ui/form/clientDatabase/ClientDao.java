package com.example.lose2gainmanagement.ui.form.clientDatabase;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ClientDao {


    @Insert
    void insert(ClientEntity clientEntity);
}
