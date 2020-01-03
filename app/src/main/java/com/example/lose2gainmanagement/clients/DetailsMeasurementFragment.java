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

public class DetailsMeasurementFragment extends Fragment {

    public DetailsMeasurementFragment() {
    }

    public DetailsMeasurementFragment(Context context, ClientEntity client) {
        this.context = context;
        this.client = client;
    }
    private TextView details_waist, details_wrist, details_chest, details_hip, details_forearm, details_bicep_left, details_bicep_right, details_thigh_left, details_thigh_right;
    private Context context;
    private ClientEntity client;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.details_measurement,container,false);

        details_waist = rootView.findViewById(R.id.details_waist);
        details_wrist = rootView.findViewById(R.id.details_wrist);
        details_chest= rootView.findViewById(R.id.details_chest);
        details_hip = rootView.findViewById(R.id.details_hip);
        details_forearm = rootView.findViewById(R.id.details_forearm);
        details_bicep_left = rootView.findViewById(R.id.details_bicep_left);
        details_bicep_right = rootView.findViewById(R.id.details_bicep_right);
        details_thigh_left = rootView.findViewById(R.id.details_thigh_left);
        details_thigh_right = rootView.findViewById(R.id.details_thigh_right);

        //Setting Text

        details_waist.setText(String.valueOf(client.getWaist()));
        details_wrist.setText(String.valueOf(client.getWrist()));
        details_chest.setText(String.valueOf(client.getChest()));
        details_hip.setText(String.valueOf(client.getHip()));
        details_forearm.setText(String.valueOf(client.getForeArm()));
        details_bicep_left.setText(String.valueOf(client.getBicept_left()));
        details_bicep_right.setText(String.valueOf(client.getBicept_right()));
        details_thigh_left.setText(String.valueOf(client.getThigh_left()));
        details_thigh_right.setText(String.valueOf(client.getThigh_right()));

        return rootView;
    }
}
