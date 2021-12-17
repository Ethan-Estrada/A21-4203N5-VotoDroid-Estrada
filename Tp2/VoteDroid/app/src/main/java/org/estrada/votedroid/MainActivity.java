package org.estrada.votedroid;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import org.estrada.votedroid.bd.BD;
import org.estrada.votedroid.databinding.ActivityMainBinding;
import org.estrada.votedroid.exceptions.MauvaiseQuestion;
import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.service.ServiceImplementation;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    QuestionAdapter mAdapter;
    private ServiceImplementation service;
    private BD maBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        maBD = Room.databaseBuilder(getApplicationContext(), BD.class,"BDQuestions")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);

        binding.btnAjouterAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PoseQActivity.class);
                startActivity(i);
            }
        });

        this.initRecycler();
        this.remplirRecycler();
    }

    private void creerQuestion() {
        try {
            VDQuestion maQuestion = new VDQuestion();
            maQuestion.texteQuestion ="AS-tu hate au nouveau film The Matrix Resurrection?";
            service.creerQuestion(maQuestion);
        }catch (MauvaiseQuestion m){
            Log.e("CREERQUESTION", "Impossible de creer la question: "+ m.getMessage() );
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.accueil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter= new QuestionAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void remplirRecycler() {
        for (VDQuestion q : service.toutesLesQuestions()){
            mAdapter.list.add(q);
        }
        mAdapter.notifyDataSetChanged();
    }
}