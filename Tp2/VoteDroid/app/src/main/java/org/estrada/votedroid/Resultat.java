package org.estrada.votedroid;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.estrada.votedroid.databinding.ActivityResultatBinding;

import java.util.ArrayList;

public class Resultat extends AppCompatActivity {

    private ActivityResultatBinding binding;
    public ArrayList<BarEntry> notes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Résultats");

        binding.txtviewMoyenne.setText("Moyenne : "+Integer.sum(2,2)       );
        binding.txtviewEcart.setText("Ecart : "+Integer.sum(3,3)       );


        BarChart barChart = findViewById(R.id.barChart);

        notes.add(new BarEntry(3,2));
        notes.add(new BarEntry(4,1));
        notes.add(new BarEntry(5,4));

        BarDataSet barDataSet = new BarDataSet(notes,"Notes");


        BarData barData = new BarData(barDataSet);

        barChart.getXChartMin();
        barChart.setFilterTouchesWhenObscured(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Description Label");
        barChart.animateY(2000);


    }
}
