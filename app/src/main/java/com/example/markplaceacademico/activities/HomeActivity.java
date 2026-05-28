package com.example.markplaceacademico.activities;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.markplaceacademico.R;
import com.example.markplaceacademico.adapters.ServicoAdapter;
import com.example.markplaceacademico.database.DatabaseHelper;
import com.example.markplaceacademico.models.Servico;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button btnCriarServico, btnLogout;

    RecyclerView recyclerServicos;

    DatabaseHelper db;

    List<Servico> listaServicos;

    ServicoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        btnCriarServico = findViewById(R.id.btnCriarServico);
        btnLogout = findViewById(R.id.btnLogout);
        recyclerServicos = findViewById(R.id.recyclerServicos);

        db = new DatabaseHelper(this);

        listaServicos = new ArrayList<>();

        recyclerServicos.setLayoutManager(
                new LinearLayoutManager(this));

        carregarServicos();

        btnCriarServico.setOnClickListener(v -> {

            Intent intent =
                    new Intent(HomeActivity.this,
                            CriarServicoActivity.class);

            startActivity(intent);
        });
        btnLogout.setOnClickListener(view -> {
            SharedPreferences s = getSharedPreferences("usuario", MODE_PRIVATE);
            SharedPreferences.Editor edit = s.edit();

            edit.putBoolean("LOGGED", false);
            edit.apply();
            
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        listaServicos.clear();

        carregarServicos();
    }
    private void carregarServicos() {

        Cursor cursor = db.listarServicos();

        while (cursor.moveToNext()) {

            Servico servico = new Servico();

            servico.setId(
                    cursor.getInt(0));

            servico.setTitulo(
                    cursor.getString(1));

            servico.setDescricao(
                    cursor.getString(2));

            servico.setCategoria(
                    cursor.getString(3));

            servico.setPreco(
                    cursor.getDouble(4));

            listaServicos.add(servico);
        }

        cursor.close();

        adapter = new ServicoAdapter(listaServicos);

        recyclerServicos.setAdapter(adapter);
    }
}