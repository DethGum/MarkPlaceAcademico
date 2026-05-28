package com.example.markplaceacademico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.markplaceacademico.R;

public class MainActivity extends AppCompatActivity {

    Button btnEntrar;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences s = getSharedPreferences("usuario", MODE_PRIVATE);
        boolean logged = s.getBoolean("LOGGED", false);

        if(logged){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }


        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnEntrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }
}