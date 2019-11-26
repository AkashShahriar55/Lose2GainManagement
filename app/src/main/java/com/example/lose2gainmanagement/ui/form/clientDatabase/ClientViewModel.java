package com.example.lose2gainmanagement.ui.form.clientDatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ClientViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();

    private ClientDao clientDao;
    private ClientDatabase clientDatabase;

    public ClientViewModel(@NonNull Application application) {
        super(application);

        clientDatabase = ClientDatabase.getDatabase(application);
        clientDao = clientDatabase.clientDao();
    }

    public void insert(ClientEntity clientEntity)
    {
        new InsertAsynkTask(clientDao).execute(clientEntity);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"ViewModel Destroyed");
    }


    private class InsertAsynkTask extends AsyncTask<ClientEntity,Void,Void>{

       ClientDao mDao;

        public InsertAsynkTask(ClientDao mDao) {
            this.mDao = mDao;
        }

        @Override
        protected Void doInBackground(ClientEntity... clientEntities) {

            mDao.insert(clientEntities[0]);
            return null;
        }
    }
}
