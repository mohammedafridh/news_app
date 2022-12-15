package com.example.thesun.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.thesun.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });

    }

}