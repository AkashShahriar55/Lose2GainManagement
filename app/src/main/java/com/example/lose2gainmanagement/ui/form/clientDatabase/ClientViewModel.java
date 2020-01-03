package com.example.lose2gainmanagement.ui.form.clientDatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ClientViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();

    private ClientDao clientDao;
    private ClientDatabase clientDatabase;

    private LiveData<List<ClientEntity>> clients;

    public ClientViewModel(@NonNull Application application) {
        super(application);

        clientDatabase = ClientDatabase.getDatabase(application);
        clientDao = clientDatabase.clientDao();
        clients = clientDao.getAllClients();
    }

    public void insert(ClientEntity clientEntity)
    {
        new InsertAsyncTask(clientDao).execute(clientEntity);
    }

    public LiveData<List<ClientEntity>> getAllClients(){
        return clients;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"ViewModel Destroyed");
    }


    private class InsertAsyncTask extends AsyncTask<ClientEntity,Void,Void>{

       ClientDao mDao;

        public InsertAsyncTask(ClientDao mDao) {
            this.mDao = mDao;
        }

        @Override
        protected Void doInBackground(ClientEntity... clientEntities) {

            mDao.insert(clientEntities[0]);
            return null;
        }
    }


    public void delete_client(ClientEntity client){

        new DeleteClientAsyncTask(clientDao).execute(client);

    }




    private class DeleteClientAsyncTask extends AsyncTask<ClientEntity,Void,Void>{
        ClientDao clientDao;
        public DeleteClientAsyncTask(ClientDao clientDao) {
            this.clientDao = clientDao;
        }

        @Override
        protected Void doInBackground(ClientEntity... clientEntities) {
            clientDao.delete_client(clientEntities[0]);
            return null;
        }
    }
}
