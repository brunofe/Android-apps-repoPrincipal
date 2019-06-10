package com.example.whatsapp.view;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.R;
import com.example.whatsapp.helper.Permissions;
import com.example.whatsapp.helper.Preferences;
import com.example.whatsapp.presenter.LoginActivityPresenter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;


public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;
    private LoginActivityPresenter presenter;
    private String[] permissoesNecessarias = new String[] {
            Manifest.permission.SEND_SMS,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Permissions.validaPermissoes(1,this,permissoesNecessarias);

        nome = findViewById(R.id.nomeLoginId);
        telefone = findViewById(R.id.telefoneNumeroLoginId);
        codPais = findViewById(R.id.telefonePaisLoginId);
        codArea = findViewById(R.id.telefoneEstadoLoginId);
        cadastrar = findViewById(R.id.buttonCadastrarId);


        /*Definir mascaras*/
        SimpleMaskFormatter simpleMaskFormatterPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskFormatterArea =   new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskFormatterTelefone = new SimpleMaskFormatter("NNNNN-NNNN");

        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskFormatterTelefone);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea,simpleMaskFormatterArea);
        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais,simpleMaskFormatterPais);


        codPais.addTextChangedListener( maskCodPais );
        codArea.addTextChangedListener( maskCodArea);
        telefone.addTextChangedListener( maskTelefone );

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto =
                                codPais.getText().toString()+
                                codArea.getText().toString()+
                                telefone.getText().toString();


                //**precisa usar o presenter para formatar os dados antes de enviar para a view
                String telefoneSemFormatacao = presenter.formatString(telefoneCompleto);

                //salvar dados para validaçao
                String token = presenter.generateToken();
                Preferences preferences = new Preferences( LoginActivity.this );
                preferences.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);


                //envio do SMS
                //precisa dar as permissoes no Manifest
                String mensagemEnvio = "WhatsApp codigo confirmacao: "+ token;
                boolean enviadoSMS = presenter.enviaSMS("+"+telefoneSemFormatacao, mensagemEnvio);


                HashMap<String, String> usuario = preferences.getDadosUsuario();

                Log.i("TOKEN","T:"+usuario.get("token")+" NOME:"+usuario.get("nome")+" FONE:"+ usuario.get("telefone"));


            }
        });
    }
}
