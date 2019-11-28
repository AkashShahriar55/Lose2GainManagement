package com.example.lose2gainmanagement.ui.DietChart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lose2gainmanagement.R;
import com.google.android.material.textfield.TextInputLayout;

public class DietChartAddBelaDialog extends Dialog {

    TextInputLayout belaName;
    Button addBelaName;
    sendBelaName listener;

    public DietChartAddBelaDialog(@NonNull Context context,sendBelaName sendBelaName) {
        super(context);
        listener = sendBelaName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_bela);

        belaName = findViewById(R.id.DietChartBelaInput);
        addBelaName = findViewById(R.id.DietChartBelaInputButton);

        addBelaName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bela;
                bela = belaName.getEditText().getText().toString();

                if(!bela.trim().isEmpty()){
                    listener.sendName(bela);
                    dismiss();
                }else{
                    belaName.setError("Fill up this field");
                    belaName.requestFocus();
                }
            }
        });
    }

    public interface sendBelaName{
        void sendName(String name);
    }
}
