package com.example.whatsapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.helper.Preferences;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

//os arquivos dessa tela foi excluida(DELETADA) na aula 183 bem como o seu xml
public class OldValidadorActivity extends AppCompatActivity {

    private EditText codigoValidacao;
    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldValidador);

        codigoValidacao = findViewById(R.id.entradaValidadorId);
        validar         = findViewById(R.id.buttonValidarId);

        //definir mascara para apenas 4 numeros
        SimpleMaskFormatter simpleMaskCodigoValidacao = new SimpleMaskFormatter(("NNNN"));
        MaskTextWatcher watcherCodigoValidacao = new MaskTextWatcher(codigoValidacao, simpleMaskCodigoValidacao);

        codigoValidacao.addTextChangedListener( watcherCodigoValidacao );
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperar dados das preferencias do usuário
                Preferences preferences = new Preferences( OldValidadorActivity.this);
                HashMap<String, String> usuario = preferences.getDadosUsuario();

                String tokenGerado = usuario.get("token");
                String tokenDigitado = codigoValidacao.getText().toString();

                if(tokenDigitado.equals(tokenGerado)) {
                    Toast.makeText(OldValidadorActivity.this, "Token VALIDADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OldValidadorActivity.this,"Token nao validado", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
