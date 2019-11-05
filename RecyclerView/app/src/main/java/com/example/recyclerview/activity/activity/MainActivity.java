package com.example.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.example.recyclerview.activity.RecyclerItemClickListener;
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
        recyclerView.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter( adapter );

        //evento de click
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(
                    getApplicationContext(),
                    recyclerView,
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Filme filme = listaFilmes.get(position);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Item pressionado "+"item "+(position+1)+" "+filme.getAno(),
                                    Toast.LENGTH_SHORT

                            ).show();
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                            Filme filme = listaFilmes.get(position);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Clique Longo "+"item "+(position+1)+" "+filme.getAno(),
                                    Toast.LENGTH_SHORT

                            ).show();
                        }

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    }
            )
        );
    }

    public void criarFilmes(){

        Filme filme = new Filme("titulo", "genero", "2017");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo2", "genero2", "2018");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo3", "genero3", "2019");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo4", "genero4", "2020");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo5", "genero5", "2021");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo6", "genero6", "2022");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo7", "genero7", "2023");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo8", "genero8", "2024");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo9", "genero9", "2025");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo10", "genero10", "2026");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo11", "genero11", "2027");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo12", "genero12", "2028");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo13", "genero13", "2029");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo14", "genero14", "2030");
        this.listaFilmes.add( filme );

        filme = new Filme("titulo15", "genero15", "2031");
        this.listaFilmes.add( filme );
    }
}
