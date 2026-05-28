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

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        SharedPreferences s = getSharedPreferences("usuario", MODE_PRIVATE);

        String l = s.getString("LOGIN", "");
        String p = s.getString("PASS", "");

        editEmailLogin = findViewById(R.id.editEmailLogin);
        editSenhaLogin = findViewById(R.id.editSenhaLogin);

        editEmailLogin.setText(l);
        editSenhaLogin.setText(p);

        btnLoginUsuario = findViewById(R.id.btnLoginUsuario);

        db = new DatabaseHelper(this);

        btnLoginUsuario.setOnClickListener(v -> {

            String email = editEmailLogin.getText().toString();
            String senha = editSenhaLogin.getText().toString();

            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean loginValido = db.verificarLogin(email, senha);

            if (loginValido) {

                SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);

                SharedPreferences.Editor edit = prefs.edit();

                edit.putString("LOGIN", email);
                edit.putString("PASS", senha);
                edit.putBoolean("LOGGED", true);
                edit.apply();

                Toast.makeText(this,
                        "Login realizado!",
                        Toast.LENGTH_SHORT).show();

                Intent intent =
                        new Intent(LoginActivity.this,
                                HomeActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            } else {

                Toast.makeText(this,
                        "Email ou senha inválidos!",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}