package com.cynoteck.petofyOPHR.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.cynoteck.petofyOPHR.R;
import com.google.android.material.card.MaterialCardView;

public class PrivacyPolicy extends AppCompatActivity {
    MaterialCardView back_arrow_CV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        back_arrow_CV               = findViewById(R.id.back_arrow_CV);
        back_arrow_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}