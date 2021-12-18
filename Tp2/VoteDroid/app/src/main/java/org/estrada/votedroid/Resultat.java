package org.estrada.votedroid;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.estrada.votedroid.bd.BD;
import org.estrada.votedroid.databinding.ActivityResultatBinding;
import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;
import org.estrada.votedroid.service.ServiceImplementation;

import java.util.ArrayList;

public class Resultat extends AppCompatActivity {

    private ActivityResultatBinding binding;
    public ArrayList<BarEntry> notes = new ArrayList<>();
    private ServiceImplementation service;
    private BD maBD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Résultats");

        maBD = Room.databaseBuilder(getApplicationContext(), BD.class,"BDQuestions")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);

        VDQuestion q = new VDQuestion();
        binding.txtviewMoyenne.setText("Moyenne : "+service.moyenneVotes(q));
        binding.txtviewEcart.setText("Ecart : "+service.ecartTypeVotes(q));


        BarChart barChart = findViewById(R.id.barChart);

        for (VDVote v : maBD.monDao().tousLesVotes(q.idQuestion)){
            notes.add(new BarEntry(v.idVote,v.rating));
        }

        BarDataSet barDataSet = new BarDataSet(notes,"Notes");
        barDataSet.setDrawValues(false);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.getDescription().setText("Description Label");
        barChart.animateY(2000);

        barChart.setMaxVisibleValueCount(6);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1); // only intervals of 1 day
        xAxis.setLabelCount(5);
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(0));

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setGranularity(1);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
    }
}
