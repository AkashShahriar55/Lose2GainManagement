package com.example.lose2gainmanagement.clients;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.DateDialog;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsMedicalProblemFragment extends Fragment {

    public DetailsMedicalProblemFragment() {
    }

    public DetailsMedicalProblemFragment(Context context, ClientEntity client) {
        this.context = context;
        this.client = client;
    }

    private Context context;
    private ClientEntity client;
    private Calendar c;
    private TextView details_last_follow_up_date;;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.details_medical_problem,container,false);

        TextView details_food_preferred = rootView.findViewById(R.id.details_food_preferred);
        TextView details_food_allergic = rootView.findViewById(R.id.details_food_allergic);
        TextView details_food_fav = rootView.findViewById(R.id.details_food_fav);
        TextView details_client_added_date = rootView.findViewById(R.id.details_client_added_date);
        TextView details_package = rootView.findViewById(R.id.details_package);
        TextView details_next_follow_up_date = rootView.findViewById(R.id.details_next_follow_up_date);

        details_last_follow_up_date = rootView.findViewById(R.id.details_last_follow_up_date);






        details_food_preferred.setText(String.valueOf(client.getFood_preferred()));
        details_food_allergic.setText(String.valueOf(client.getFood_allergic()));
        details_food_fav.setText(String.valueOf(client.getFood_fav()));
        details_client_added_date.setText(String.valueOf(client.getClient_added_date()));
        String pkg =client.getPackages() + " Weeks";
        details_package.setText(pkg);
        details_next_follow_up_date.setText(String.valueOf(client.getNext_followup()));
        details_last_follow_up_date.setText(String.valueOf(client.getNext_followup()));


        return rootView;
    }




}
