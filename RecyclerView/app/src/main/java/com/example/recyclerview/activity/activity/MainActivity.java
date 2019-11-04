package com.example.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview.R;
import com.example.recyclerview.activity.adapter.Adapter;
import com.example.recyclerview.activity.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        ////Listagem de filmes
        this.criarFilmes();

        //Configurar adapter
        Adapter adapter = new Adapter( listaFilmes );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager( layoutManager );
        /** metodo qu diz que o recycle view vai ter tamanho fixo **/
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( adapter );
    }

    public void criarFilmes(){

        Filme filme = new Filme("titulo", "genero", "2017");
        this.listaFilmes.add( filme );



    }
}
