package com.example.markplaceacademico.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.markplaceacademico.R;
import com.example.markplaceacademico.database.DatabaseHelper;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome, editEmail, editSenha;
    Button btnCadastrarUsuario;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        db = new DatabaseHelper(this);

        btnCadastrarUsuario.setOnClickListener(v -> {

            String nome = editNome.getText().toString();
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();

            boolean sucesso = db.inserirUsuario(nome, email, senha);

            if (sucesso) {

                Toast.makeText(this,
                        "Usuário cadastrado com sucesso!",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this,
                        "Erro ao cadastrar usuário!",
                        Toast.LENGTH_SHORT).show();
            }});
    }
}