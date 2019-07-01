package com.example.whatsapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.helper.Base64Custom;
import com.example.whatsapp.helper.Preferences;
import com.example.whatsapp.model.Mensagem;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ConversaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;
    private ListView listView;
    private ArrayList<String> mensagens;
    private ArrayAdapter adapter;

    //dados detinatario
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;

    //dados do remetente
    private String idUsuarioRemetente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        toolbar = findViewById((R.id.tb_conversa));
        editMensagem = findViewById(R.id.edit_mensagem);
        btMensagem = findViewById(R.id.bt_enviar);

        // dados do usuário logado
        Preferences preferences = new Preferences(ConversaActivity.this);
        idUsuarioRemetente = preferences.getIdentificador();

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
        mensagens.add("Mensagem1");
        adapter = new ArrayAdapter(
                ConversaActivity.this,
                android.R.layout.simple_list_item_1,
                mensagens

        );
        listView.setAdapter(adapter);

        //Recuperar mensagens do firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                                        .child("mensagens")
                                        .child(idUsuarioRemetente)
                                        .child(idUsuarioDestinatario);
// *******************************  6:10   da aula: 233    ****************************
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

                    salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);
                    /*
                        mensagens
                            + email que esta fazendo o envio
                                + destinatario de uma mensagem
                                    +mensagem
                                + destinatario de outra mensagem
                                    +mensagem

                     */

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
}
