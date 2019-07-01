package com.example.whatsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.whatsapp.R;
import com.example.whatsapp.helper.Preferences;
import com.example.whatsapp.model.Mensagem;

import java.util.ArrayList;

public class MensagemAdapter extends ArrayAdapter<Mensagem> {

    private Context context;
    private ArrayList<Mensagem> mensagems;

    public MensagemAdapter(Context c, ArrayList<Mensagem> objects) {
        super(c, 0, objects);
        this.context = c;
        this.mensagems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        //verifica se a lista está preenchida
        if(mensagems != null){

            // Recupera dados do usuario remetente
            Preferences preferencias = new Preferences(context);
            String idUsuarioRemetente = preferencias.getIdentificador();

            //Inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Recupera mensagem
            Mensagem mensagem = mensagems.get(position);

            // Monta view a partir do xml
            if(idUsuarioRemetente.equals(mensagem.getIdUsuario())){
                //quando faz o envio da mensagem
                view = inflater.inflate(R.layout.item_mensagem_direita,parent,false);
            } else {
                // quando recebe a mensagem
                view = inflater.inflate(R.layout.item_mensagem_esquerda,parent,false);
            }

            // Recupera elemento para exibição
            TextView textoMensagem = view.findViewById(R.id.tv_mensagem);
            textoMensagem.setText(mensagem.getMensagem());
        }

        return view;
    }
}
