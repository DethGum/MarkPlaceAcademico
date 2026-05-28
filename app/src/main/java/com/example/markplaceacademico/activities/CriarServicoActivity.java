package com.example.markplaceacademico.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.markplaceacademico.R;
import com.example.markplaceacademico.database.DatabaseHelper;

public class CriarServicoActivity extends AppCompatActivity {

    EditText editTitulo, editDescricao,
            editCategoria, editPreco;

    Button btnSalvarServico;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_servico);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        editCategoria = findViewById(R.id.editCategoria);
        editPreco = findViewById(R.id.editPreco);

        btnSalvarServico = findViewById(R.id.btnSalvarServico);

        db = new DatabaseHelper(this);

        btnSalvarServico.setOnClickListener(v -> {

            String titulo =
                    editTitulo.getText().toString();

            String descricao =
                    editDescricao.getText().toString();

            String categoria =
                    editCategoria.getText().toString();

            double preco =
                    Double.parseDouble(
                            editPreco.getText().toString());
            SharedPreferences preferences =
                    getSharedPreferences(
                            "user_session",
                            MODE_PRIVATE);

            String emailUsuario =
                    preferences.getString(
                            "email",
                            "");
            boolean sucesso =
                    db.inserirServico(
                            titulo,
                            descricao,
                            categoria,
                            preco,
                            emailUsuario
                    );

            if (sucesso) {

                Toast.makeText(this,
                        "Serviço publicado!",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this,
                        "Erro ao publicar serviço!",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}