package org.estrada.votedroid;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.estrada.votedroid.databinding.ActivityResultatBinding;

public class Resultat extends AppCompatActivity {

    private ActivityResultatBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}
