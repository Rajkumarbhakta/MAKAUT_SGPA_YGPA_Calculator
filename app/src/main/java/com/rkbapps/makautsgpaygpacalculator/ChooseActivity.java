package com.rkbapps.makautsgpaygpacalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseActivity extends AppCompatActivity {
    Button sgpaToPercentage, ygpaToPercentage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        sgpaToPercentage = findViewById(R.id.button_sgpa_to_percentage);
        ygpaToPercentage = findViewById(R.id.button_ygpa_to_percentage);

        sgpaToPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseActivity.this, SgpaToPercentageActivity.class);
                startActivity(i);
            }
        });
        ygpaToPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}