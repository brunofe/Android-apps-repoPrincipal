package com.example.whatsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.whatsapp.R;
import com.example.whatsapp.model.Conversa;

import java.util.ArrayList;

public class ConversaAdapter extends ArrayAdapter {

    private ArrayList<Conversa> conversas;
    private Context context;

    public ConversaAdapter(Context c, ArrayList<Conversa> objects) {
        super(c, 0, objects);
        this.context = c;
        this.conversas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        // Verifica se a lista está preenchida
        if(conversas != null) {

            // inicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Monta view a partir do xml
            view = inflater.inflate(R.layout.lista_conversas, parent, false);

            // recupera elemento para exibicao
            TextView nome = view.findViewById(R.id.tv_titulo);
            TextView ultimaMensagem = view.findViewById(R.id.tv_subtitulo);

            Conversa conversa = conversas.get(position);
            nome.setText(conversa.getNome());
            ultimaMensagem.setText(conversa.getMensagem());
        }

        return view;
    }
}
