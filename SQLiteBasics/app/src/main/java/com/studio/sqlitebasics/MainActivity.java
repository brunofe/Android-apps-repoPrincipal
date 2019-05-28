package com.studio.sqlitebasics;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

        //CRIANDO tabela SE NAO EXISTE
        //bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3))");

        //criando tabela com id INCREMENTAL..  1 bruno, 2 bruno,  3 eduardo, 4 janete ... etc
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR, idade INT(3))");
        //deletando a tabela
        //bancoDados.execSQL("drop table pessoas");


        //Inserir dados
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"Carlos\", 43)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"Eduardo\", 40)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"Adriana\", 40)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"Rodrigo\", 40)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"marcelo\", 40)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"Eduardo\", 40)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES (\"Eduardo\", 40)");

        //modificar dados
        //bancoDados.execSQL("UPDATE pessoas SET idade = 37 WHERE nome = \"Eduardo\"");

        //deletar dados
        //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = \"Carlos\"");

        //WHERE
        //Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas Where idade>30 AND nome = \"Eduardo\" ", null);
        Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas", null);

        int indiceColunaNome = cursor.getColumnIndex("nome");
        int indiceColunaIdade = cursor.getColumnIndex("idade");
        int indiceColunaId = cursor.getColumnIndex("id");

        cursor.moveToFirst();
        // colocar RESULTADO no logcat para visualizar
        while( cursor != null) {
            Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
            Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));
            Log.i("RESULTADO - id: ", cursor.getString(indiceColunaId));
            cursor.moveToNext();
        }


    }
}
