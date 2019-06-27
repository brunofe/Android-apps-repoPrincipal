package com.example.whatsapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whatsapp.Adapter.ContatoAdapter;
import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.helper.Preferences;
import com.example.whatsapp.model.Contato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ContatosFragment extends Fragment {
    public ListView listView;
    //precisa  adapter para exibir os dados em uma list view
    private ArrayAdapter adapter;
    private ArrayList<Contato> contatos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerContatos;

    public ContatosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerContatos);
        Log.i("ValueEventListener","onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerContatos);
        Log.i("ValueEventListener","onStop");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Instanciar objetos
        contatos = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        //Monta listview e adapter
        listView = view.findViewById(R.id.lv_contatos);
        /*
        adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_contato,
                contatos
        );
        */
        adapter = new ContatoAdapter(getActivity(), contatos);
        listView.setAdapter(adapter);

        //recuperar contatos do firebase
        Preferences preferencias = new Preferences(getActivity());
        String identificadorUsuario = preferencias.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                                       .child("contatos")
                                       .child(identificadorUsuario);

        //Listener para recuperar contatos
        valueEventListenerContatos = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Limpar lista
                contatos.clear();

                //listar contatos
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Contato contato = dados.getValue(Contato.class);
                    contatos.add(contato);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        return view;
    }
}