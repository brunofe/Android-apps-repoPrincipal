package com.studio.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textoNome   = findViewById(R.id.inputTextId);
        botaoSalvar = findViewById(R.id.buttonSalvarId);
        textoExibicao = findViewById(R.id.textExibicaoId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Context.MODE_PRIVATE para privado.
                //Context.MODE_APPEND para acrescentar
                //etc
                SharedPreferences sharedpreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if( textoNome.getText().toString().equals("") ) {
                    Toast.makeText(MainActivity.this, "Por favor, preencher o nome", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("nome", textoNome.getText().toString());
                    editor.commit();
                    textoExibicao.setText("Olá, " + textoNome.getText().toString());
                }
            }
        });

        //Recuperar os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE);
        if( sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "usuário não definido");
            textoExibicao.setText(nomeUsuario);
        } else {
            textoExibicao.setText("Olá, usuáio não definido.");
        }
    }
}
