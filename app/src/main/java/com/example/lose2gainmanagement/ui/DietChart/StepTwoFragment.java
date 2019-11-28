package com.example.lose2gainmanagement.ui.DietChart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lose2gainmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class StepTwoFragment extends Fragment implements DietChartAddBelaDialog.sendBelaName {

    private List<DietChartBela> belaItems;
    private DietChartBelaAdapter adapter ;
    private RecyclerView recyclerViewBela;
    private Button addBela,next;
    private StepTwoNext stepTwoNext;

    public StepTwoFragment(List<DietChartBela> belaItems, StepTwoNext stepTwoNext) {
        this.belaItems =belaItems;
        this.stepTwoNext = stepTwoNext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_step_two_fragment, container, false);

        recyclerViewBela = root.findViewById(R.id.DietChartBelaRV);
        addBela = root.findViewById(R.id.DietChartAddBela);
        next = root.findViewById(R.id.DietChartStepTwoNext);

        adapter = new DietChartBelaAdapter(belaItems,getContext());
        recyclerViewBela.setAdapter(adapter);
        recyclerViewBela.setLayoutManager(new LinearLayoutManager(getContext()));

        addBela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DietChartAddBelaDialog(getContext(),StepTwoFragment.this).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepTwoNext.next();
            }
        });
        return root;
    }

    @Override
    public void sendName(String name) {
        belaItems.add(new DietChartBela(name,new ArrayList<DietChartFood>()));
        adapter.notifyDataSetChanged();
    }

    public interface StepTwoNext{
        void next();
    }
}
