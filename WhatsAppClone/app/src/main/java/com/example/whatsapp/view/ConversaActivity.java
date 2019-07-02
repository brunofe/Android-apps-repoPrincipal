package com.example.whatsapp.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.whatsapp.Adapter.MensagemAdapter;
import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.helper.Base64Custom;
import com.example.whatsapp.helper.Preferences;
import com.example.whatsapp.model.Conversa;
import com.example.whatsapp.model.Mensagem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConversaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private ArrayAdapter<Mensagem> adapter;
    private ValueEventListener valueEventListener;

    //dados detinatario
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;

    //dados do remetente
    private String idUsuarioRemetente;
    private String nomeUsuarioRemetente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        toolbar = findViewById((R.id.tb_conversa));
        editMensagem = findViewById(R.id.edit_mensagem);
        btMensagem = findViewById(R.id.bt_enviar);
        listView = findViewById(R.id.lv_conversas);

        // dados do usuário logado
        Preferences preferences = new Preferences(ConversaActivity.this);
        idUsuarioRemetente = preferences.getIdentificador();
        nomeUsuarioRemetente = preferences.getNome();

        Bundle extra = getIntent().getExtras();

        if(extra != null) {
            nomeUsuarioDestinatario = extra.getString("nome");
            String emailDestinatario = extra.getString("email");
            idUsuarioDestinatario = Base64Custom.codificarBase64(emailDestinatario);
        }

        //configura toolbar
        toolbar.setTitle(nomeUsuarioDestinatario);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        //Monta listview e adapter
        mensagens = new ArrayList<>();
        adapter   = new MensagemAdapter(ConversaActivity.this, mensagens);
        listView.setAdapter(adapter);

        //Recuperar mensagens do firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                                        .child("mensagens")
                                        .child(idUsuarioRemetente)
                                        .child(idUsuarioDestinatario);

        // Cria listener para mensagens
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // limpar mensagens
                mensagens.clear();

                //recupera mensagens
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Mensagem mensagem = dados.getValue(Mensagem.class);
                    mensagens.add(mensagem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener(valueEventListener);


        //Enviar mensagem
        btMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoMensagem = editMensagem.getText().toString();

                if(textoMensagem.isEmpty()){
                    Toast.makeText(ConversaActivity.this, "Digite uma mensagem para enviar", Toast.LENGTH_SHORT).show();
                }else {

                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idUsuarioRemetente);
                    mensagem.setMensagem(textoMensagem);

                    //Salvando mensagem para remetente
                    Boolean retornoMensagemRemetente = salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);
                    if(!retornoMensagemRemetente) {
                        Toast.makeText(ConversaActivity.this,
                                "Problema ao salvar mensagem, tente novamente!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        //Salvando mensagem para destinatario
                        Boolean retornoMensagemDestinatario = salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);
                        if(!retornoMensagemDestinatario){
                            Toast.makeText(ConversaActivity.this,
                                    "Problema ao enviar mensagem ao destinatario tente novamente!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    /*
                        mensagens
                            + email que esta fazendo o envio
                                + destinatario de uma mensagem
                                    +mensagem
                                + destinatario de outra mensagem
                                    +mensagem

                     */

                    // salvamos conversa para o remetente
                    Conversa conversa = new Conversa();
                    conversa.setIdUsuario(idUsuarioDestinatario);
                    conversa.setNome(nomeUsuarioDestinatario);
                    conversa.setMensagem(textoMensagem);
                    Boolean retornoConversaRemetente = salvarConversa(idUsuarioRemetente,idUsuarioDestinatario, conversa);
                    if(!retornoConversaRemetente){
                        if(!retornoConversaRemetente){
                            Toast.makeText(
                                    ConversaActivity.this,
                                    "Problema ao salvar conversa, tente novamente!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        // salvamos Coversa para o Destinatario
                        conversa = new Conversa();
                        conversa.setIdUsuario(idUsuarioRemetente);
                        conversa.setNome( nomeUsuarioRemetente );
                        conversa.setMensagem(textoMensagem);
                        Boolean retornoConversaDesinatario = salvarConversa(idUsuarioDestinatario, idUsuarioRemetente, conversa);
                        if(!retornoConversaDesinatario){
                            Toast.makeText(ConversaActivity.this,"Problem ao salvar conversa para o destinatario, tete novamente!",Toast.LENGTH_SHORT).show();
                        }



                    }
                    editMensagem.setText("");

                }
            }
        });

    }

    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem) {
            try{
                firebase = ConfiguracaoFirebase.getFirebase().child("mensagens");
                firebase.child(idRemetente)
                        .child(idDestinatario)
                        .push()
                        .setValue(mensagem);

                return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }

    }

    private boolean salvarConversa(String idRemetente, String idDestinatario, Conversa conversa){
        try {
            firebase = ConfiguracaoFirebase.getFirebase().child("convesas");
            firebase.child(idRemetente).child(idDestinatario).setValue( conversa );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListener);
    }
}
