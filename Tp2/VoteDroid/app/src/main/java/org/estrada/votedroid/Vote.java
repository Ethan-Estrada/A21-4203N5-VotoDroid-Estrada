package org.estrada.votedroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.estrada.votedroid.bd.BD;
import org.estrada.votedroid.databinding.ActivityVoteBinding;
import org.estrada.votedroid.exceptions.MauvaisVote;
import org.estrada.votedroid.exceptions.MauvaiseQuestion;
import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;
import org.estrada.votedroid.service.ServiceImplementation;

public class Vote extends AppCompatActivity {
    private ActivityVoteBinding binding;
    private ServiceImplementation service;
    private BD maBD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        maBD = Room.databaseBuilder(getApplicationContext(), BD.class,"BDQuestions")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);


        binding.btnAjouterVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerVote(binding.editTxtVote.getText().toString(),binding.ratingBar.getNumStars());
                String q = creerVote(binding.editTxtVote.getText().toString(),binding.ratingBar.getNumStars());
                if (q != null){
                    Toast.makeText(getApplicationContext(),q, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Vote ajouté!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Vote.this, MainActivity.class));
                }
            }
        });
    }

    private String creerVote(String texte, int nmbrEtoiles) {
        String lol2;
        try {
            VDVote monVote = new VDVote();
            monVote.nomDuVotant = texte;
            monVote.rating = nmbrEtoiles;
            service.creerVote(monVote);
        }catch (MauvaisVote m){
            Log.e("CREERVOTE", "Impossible de creer le vote: "+ m.getMessage());
            lol2 = m.getMessage();
            return lol2 ;
        }
        return null;
    }


}
