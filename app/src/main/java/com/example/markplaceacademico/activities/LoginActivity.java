package com.example.markplaceacademico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.markplaceacademico.R;
import com.example.markplaceacademico.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText editEmailLogin, editSenhaLogin;
    Button btnLoginUsuario;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmailLogin = findViewById(R.id.editEmailLogin);
        editSenhaLogin = findViewById(R.id.editSenhaLogin);

        btnLoginUsuario = findViewById(R.id.btnLoginUsuario);

        db = new DatabaseHelper(this);

        btnLoginUsuario.setOnClickListener(v -> {

            String email = editEmailLogin.getText().toString();
            String senha = editSenhaLogin.getText().toString();

            boolean loginValido = db.verificarLogin(email, senha);

            if (loginValido) {

                SharedPreferences prefs =
                        getSharedPreferences("usuario", MODE_PRIVATE);

                prefs.edit()
                        .putString("email", email)
                        .apply();

                Toast.makeText(this,
                        "Login realizado!",
                        Toast.LENGTH_SHORT).show();

                Intent intent =
                        new Intent(LoginActivity.this,
                                HomeActivity.class);

                startActivity(intent);

            } else {

                Toast.makeText(this,
                        "Email ou senha inválidos!",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}