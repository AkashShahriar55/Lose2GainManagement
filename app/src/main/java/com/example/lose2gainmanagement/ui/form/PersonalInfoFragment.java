package com.example.lose2gainmanagement.ui.form;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lose2gainmanagement.AddClient;
import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PersonalInfoFragment extends Fragment {
    private Context context;
    private Spinner input_sex;

    private TextInputLayout edt_name,edt_occupation,edt_phone,edt_address,edt_height,edt_weight,edt_age;

    private String name,occupation,phone,address,height,weight,age,sex;

    private ClientEntity client;

    public PersonalInfoFragment() {
    }

    public PersonalInfoFragment(Context context, ClientEntity client) {
        this.context = context;
        this.client = client;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.form_personal_info,container,false);
        input_sex = rootView.findViewById(R.id.input_sex);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,R.array.sex,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        input_sex.setAdapter(adapter);
        //input_sex.setOnItemClickListener((AdapterView.OnItemClickListener) context);

        //editText Initialization
        edt_name = rootView.findViewById(R.id.edt_name);
        edt_occupation = rootView.findViewById(R.id.edt_occupation);
        edt_phone = rootView.findViewById(R.id.edt_phone);
        edt_address = rootView.findViewById(R.id.edt_address);
        edt_height = rootView.findViewById(R.id.edt_height);
        edt_weight = rootView.findViewById(R.id.edt_weight);
        edt_age = rootView.findViewById(R.id.edt_age);

        return rootView;
    }

    private boolean Validate(){
        name = edt_name.getEditText().getText().toString().trim();
        occupation = edt_occupation.getEditText().getText().toString().trim();
        phone = edt_phone.getEditText().getText().toString().trim();
        address = edt_address.getEditText().getText().toString().trim();
        height = edt_height.getEditText().getText().toString().trim();
        weight = edt_weight.getEditText().getText().toString().trim();
        sex = input_sex.getSelectedItem().toString();
        age = edt_age.getEditText().getText().toString().trim();


        edt_name.setErrorEnabled(false);
        edt_name.setError(null);

        edt_occupation.setErrorEnabled(false);
        edt_occupation.setError(null);

        edt_phone.setErrorEnabled(false);
        edt_phone.setError(null);

        edt_address.setErrorEnabled(false);
        edt_address.setError(null);

        edt_height.setErrorEnabled(false);
        edt_height.setError(null);

        edt_weight.setErrorEnabled(false);
        edt_weight.setError(null);

        edt_age.setErrorEnabled(false);
        edt_age.setError(null);


        if (name.isEmpty()){
            edt_name.setErrorEnabled(true);
            edt_name.setError("Field Can't be empty");
            return false;
        }

        else if(occupation.isEmpty()){

            edt_occupation.setErrorEnabled(true);
            edt_occupation.setError("Field Can't be empty");
            return false;
        }

        else if (phone.isEmpty()){

            edt_phone.setErrorEnabled(true);
            edt_phone.setError("Field Can't be empty");
            return false;
        }

        else if(phone.length() != 11){
            edt_phone.setErrorEnabled(true);
            edt_phone.setError("Please Enter Valid Phone Number");
            return false;
        }

        else if (address.isEmpty()){

            edt_address.setErrorEnabled(true);
            edt_address.setError("Field Can't be empty");
            return false;
        }

        else if (height.isEmpty()){

            edt_height.setErrorEnabled(true);
            edt_height.setError("Field Can't be empty");
            return false;
        }

        else if (weight.isEmpty()){

            edt_weight.setErrorEnabled(true);
            edt_weight.setError("Field Can't be empty");
            return false;
        }

        else if (age.isEmpty()){

            edt_age.setErrorEnabled(true);
            edt_age.setError("Field Can't be empty");
            return false;
        }

        else{

            return true;
        }

    }

    public boolean confirmPersonalInfo(){

        if (Validate()){
            client.setName(name);
            client.setOccupation(occupation);
            client.setPhone_no(phone);
            client.setAddress(address);
            client.setHeight(height);
            client.setWeight(weight);
            client.setSex(sex);
            client.setAge(age);
            return true;
        }

        else {
            return false;
        }
    }
}
