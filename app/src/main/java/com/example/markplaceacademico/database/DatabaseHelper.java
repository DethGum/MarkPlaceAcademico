package com.example.markplaceacademico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "marketplace.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_SERVICOS = "servicos";

    public static final String COL_ID = "id";
    public static final String COL_NOME = "nome";
    public static final String COL_EMAIL = "email";
    public static final String COL_SENHA = "senha";

    public static final String COL_TITULO = "titulo";
    public static final String COL_DESCRICAO = "descricao";
    public static final String COL_CATEGORIA = "categoria";
    public static final String COL_PRECO = "preco";
    public static final String COL_USUARIO_EMAIL = "usuario_email";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUsuariosTable = "CREATE TABLE " + TABLE_USUARIOS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOME + " TEXT, "
                + COL_EMAIL + " TEXT, "
                + COL_SENHA + " TEXT)";

        String createServicosTable = "CREATE TABLE " + TABLE_SERVICOS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TITULO + " TEXT, "
                + COL_DESCRICAO + " TEXT, "
                + COL_CATEGORIA + " TEXT, "
                + COL_PRECO + " REAL, "
                + COL_USUARIO_EMAIL + " TEXT)";

        db.execSQL(createUsuariosTable);
        db.execSQL(createServicosTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean inserirUsuario(String nome, String email, String senha) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_NOME, nome);
        values.put(COL_EMAIL, email);
        values.put(COL_SENHA, senha);

        long resultado = db.insert(TABLE_USUARIOS, null, values);

        return resultado != -1;
    }


    public boolean verificarLogin(String email, String senha) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_USUARIOS
                + " WHERE " + COL_EMAIL + " = ? AND "
                + COL_SENHA + " = ?";

        Cursor cursor = db.rawQuery(query,
                new String[]{email, senha});

        boolean existe = cursor.getCount() > 0;

        cursor.close();

        return existe;
    }


    public boolean inserirServico(String titulo,
                                  String descricao,
                                  String categoria,
                                  double preco) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TITULO, titulo);
        values.put(COL_DESCRICAO, descricao);
        values.put(COL_CATEGORIA, categoria);
        values.put(COL_PRECO, preco);

        long resultado = db.insert(TABLE_SERVICOS,
                null,
                values);


        return resultado != -1;
    }
    public Cursor listarServicos() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_SERVICOS,
                null
        );
    }
}