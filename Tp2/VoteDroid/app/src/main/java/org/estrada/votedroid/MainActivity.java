package org.estrada.votedroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import org.estrada.votedroid.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    QuestionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAjouterAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PoseQActivity.class);
                startActivity(i);
            }
        });

        this.initRecycler();
        this.remplirRecycler("Que penses tu des pommes ?");
        this.remplirRecycler("Que penses tu des poires ?");
        this.remplirRecycler("Que penses tu des bananes ?");
        this.remplirRecycler("Que penses tu des mangues ?");
        this.remplirRecycler("Que penses tu des limes ?");
        this.remplirRecycler("Que penses tu des citrons ?");
        this.remplirRecycler("Que penses tu des mandarines ?");
        this.remplirRecycler("Que penses tu des oranges ?");
        this.remplirRecycler("Que penses tu des fraises ?");
        this.remplirRecycler("Que penses tu des figues ?");
        this.remplirRecycler("Que penses tu des avocats ?");
        this.remplirRecycler("Que penses tu des raisins ?");
        this.remplirRecycler("Que penses tu des clémentines ?");
        this.remplirRecycler("Que penses tu des tomates ?");
        this.remplirRecycler("Que penses tu des abricots ?");
        this.remplirRecycler("Que penses tu des ananas ?");
        this.remplirRecycler("Que penses tu des kakis ?");
        this.remplirRecycler("Que penses tu des kiwis ?");
        this.remplirRecycler("Que penses tu des melons ?");
        this.remplirRecycler("Que penses tu des papayes ?");
        this.remplirRecycler("Que penses tu des pêches ?");


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

    private void remplirRecycler(String texte) {
            Question q = new Question();
            q.LaQuestion = texte;
            mAdapter.list.add(q);
        mAdapter.notifyDataSetChanged();
    }
}