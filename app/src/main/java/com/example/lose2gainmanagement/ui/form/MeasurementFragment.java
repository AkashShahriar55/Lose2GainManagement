package com.example.lose2gainmanagement.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MeasurementFragment extends Fragment {

    private TextInputLayout edt_waist, edt_wrist, edt_chest, edt_hip, edt_foreArm, edt_bicep_left, edt_bicep_right, edt_thigh_left, edt_thigh_right;
    private String waist,wrist,chest,hip,foreArm,bicept_left,bicept_right,thight_left,thigh_right;

    private ClientEntity client;

    public MeasurementFragment() {
    }

    public MeasurementFragment(ClientEntity client) {
        this.client = client;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.form_measurement,container,false);

        //editText Initialization

        edt_waist = rootView.findViewById(R.id.edt_waist);
        edt_wrist = rootView.findViewById(R.id.edt_wrist);
        edt_chest = rootView.findViewById(R.id.edt_chest);
        edt_hip = rootView.findViewById(R.id.edt_hip);
        edt_foreArm = rootView.findViewById(R.id.edt_foreArm);
        edt_bicep_left = rootView.findViewById(R.id.edt_bicep_left);
        edt_bicep_right =rootView.findViewById(R.id.edt_bicep_right);
        edt_thigh_left = rootView.findViewById(R.id.edt_thigh_left);
        edt_thigh_right = rootView.findViewById(R.id.edt_thigh_right);

        return rootView;
    }

    private boolean Validate()
    {
        waist = edt_waist.getEditText().getText().toString().trim();
        wrist = edt_wrist.getEditText().getText().toString().trim();
        chest = edt_chest.getEditText().getText().toString().trim();
        hip = edt_hip.getEditText().getText().toString().trim();
        foreArm = edt_foreArm.getEditText().getText().toString().trim();
        bicept_left = edt_bicep_left.getEditText().getText().toString().trim();
        bicept_right = edt_bicep_right.getEditText().getText().toString().trim();
        thight_left = edt_thigh_left.getEditText().getText().toString().trim();
        thigh_right = edt_thigh_right.getEditText().getText().toString().trim();

        edt_waist.setErrorEnabled(false);
        edt_waist.setError(null);

        edt_wrist.setErrorEnabled(false);
        edt_wrist.setError(null);

        edt_chest.setErrorEnabled(false);
        edt_chest.setError(null);

        edt_hip.setErrorEnabled(false);
        edt_hip.setError(null);

        edt_foreArm.setErrorEnabled(false);
        edt_foreArm.setError(null);

        edt_bicep_left.setErrorEnabled(false);
        edt_bicep_left.setError(null);

        edt_bicep_right.setErrorEnabled(false);
        edt_bicep_right.setError(null);

        edt_thigh_left.setErrorEnabled(false);
        edt_thigh_left.setError(null);

        edt_thigh_right.setErrorEnabled(false);
        edt_thigh_right.setError(null);

        if (waist.isEmpty()){
            edt_waist.setErrorEnabled(true);
            edt_waist.setError("Field Can't be empty");
            return false;
        }

        else if (wrist.isEmpty()){

            edt_wrist.setErrorEnabled(true);
            edt_wrist.setError("Field Can't be empty");
            return false;
        }

        else if (chest.isEmpty()){
            edt_chest.setErrorEnabled(true);
            edt_chest.setError("Field Can't be empty");
            return false;
        }

        else if (hip.isEmpty()){

            edt_hip.setErrorEnabled(true);
            edt_hip.setError("Field Can't be empty");
            return false;
        }

        else if (foreArm.isEmpty()){

            edt_foreArm.setErrorEnabled(true);
            edt_foreArm.setError("Field Can't be empty");
            return false;
        }

        else if (bicept_left.isEmpty()){
            edt_bicep_left.setErrorEnabled(true);
            edt_bicep_left.setError("Field Can't be empty");
            return false;
        }

        else if (bicept_right.isEmpty()){

            edt_bicep_right.setErrorEnabled(true);
            edt_bicep_right.setError("Field Can't be empty");
            return false;
        }

        else if (thight_left.isEmpty()){

            edt_thigh_left.setErrorEnabled(true);
            edt_thigh_left.setError("Field Can't be empty");
            return false;
        }

        else if (thigh_right.isEmpty()){

            edt_thigh_right.setErrorEnabled(true);
            edt_thigh_right.setError("Field Can't be empty");
            return false;
        }


        else{

            return  true;
        }
    }

    public boolean confirmMeasurement(){
        if (Validate()){

            client.setWaist(waist);
            client.setWrist(wrist);
            client.setChest(chest);
            client.setHip(hip);
            client.setForeArm(foreArm);
            client.setBicept_left(bicept_left);
            client.setBicept_right(bicept_right);
            client.setThigh_left(thight_left);
            client.setThigh_right(thigh_right);

            return true;
        }

        else{
            return false;
        }
    }
}
