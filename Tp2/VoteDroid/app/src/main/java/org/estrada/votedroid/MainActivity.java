package org.estrada.votedroid;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.accueil_menu,menu);
                return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menu1:
            service.supprimerQuestions();
            mAdapter.notifyDataSetChanged();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            return true;
            case  R.id.menu2:
                service.supprimerVotes();
                mAdapter.notifyDataSetChanged();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
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