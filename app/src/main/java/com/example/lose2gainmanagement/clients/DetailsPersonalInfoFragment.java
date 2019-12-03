package com.example.lose2gainmanagement.clients;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsPersonalInfoFragment extends Fragment {


    public DetailsPersonalInfoFragment() {
    }

    public DetailsPersonalInfoFragment(Context context , ClientEntity client) {
        this.client = client;
    }

    private ClientEntity client;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.details_personal_info,container,false);
        TextView details_name = rootView.findViewById(R.id.details_name);
        TextView details_occupation = rootView.findViewById(R.id.details_occupation);
        TextView details_phone = rootView.findViewById(R.id.details_phone);
        TextView details_address = rootView.findViewById(R.id.details_address);
        TextView details_height = rootView.findViewById(R.id.details_height);
        TextView details_weight = rootView.findViewById(R.id.details_weight);
        TextView details_sex = rootView.findViewById(R.id.details_sex);
        TextView details_age = rootView.findViewById(R.id.details_age);

        //setting Text

        details_name.setText(String.valueOf(client.getName()));
        details_occupation.setText(String.valueOf(client.getOccupation()));
        details_phone.setText(String.valueOf(client.getPhone_no()));
        details_address.setText(String.valueOf(client.getAddress()));
        details_height.setText(String.valueOf(client.getHeight()));
        details_weight.setText(String.valueOf(client.getWeight()));
        details_sex.setText(String.valueOf(client.getSex()));
        details_age.setText(String.valueOf(client.getAge()));
        return rootView;
    }
}
