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
import org.estrada.votedroid.databinding.ActivityPoseBinding;
import org.estrada.votedroid.exceptions.MauvaiseQuestion;
import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.service.ServiceImplementation;

public class PoseQActivity extends AppCompatActivity {

    private String lol;
    private ActivityPoseBinding binding;
    private ServiceImplementation service;
    private BD maBD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPoseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        maBD = Room.databaseBuilder(getApplicationContext(), BD.class,"BDQuestions")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);



        binding.btnPoseQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerQuestion(binding.editTxtQuestion.getText().toString());
                String q = creerQuestion(binding.editTxtQuestion.getText().toString());
                if (q != null){
                    Toast.makeText(getApplicationContext(),q, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public String creerQuestion(String textQ){
        String lol2;
        try {
            VDQuestion maQuestion = new VDQuestion();
            maQuestion.texteQuestion = textQ;
            service.creerQuestion(maQuestion);
        }catch (MauvaiseQuestion m){
            Log.e("CREERQUESTION", "Impossible de creer la question: "+ m.getMessage());
            lol2 = m.getMessage();
            return lol2 ;
        }
        return null;
    }
}
