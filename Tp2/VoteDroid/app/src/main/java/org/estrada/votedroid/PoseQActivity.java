package org.estrada.votedroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.estrada.votedroid.databinding.ActivityPoseBinding;

public class PoseQActivity extends AppCompatActivity {

    private ActivityPoseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPoseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnPoseQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PoseQActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
