package com.example.lose2gainmanagement.ui.form.clientDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = ClientEntity.class,version = 1)
public abstract class ClientDatabase extends RoomDatabase {

    public abstract ClientDao clientDao();

    private static volatile ClientDatabase clientInstance;

    static ClientDatabase getDatabase(final Context context)
    {
        if (clientInstance == null){
            synchronized (ClientDatabase.class){
                if (clientInstance ==null){
                    clientInstance = Room.databaseBuilder(context.getApplicationContext(),ClientDatabase.class,"client_database").build();
                }
            }
        }

        return clientInstance;
    }
}
