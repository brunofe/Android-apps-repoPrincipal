package com.studio.minhasanotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private ImageView botonSave;
    private static final String NOME_ARQUIVO =  "arquivo_anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textoId);
        botonSave = findViewById(R.id.imgViewSalvar);
        botonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoDigitado = text.getText().toString();
                gravarNoArquivo( textoDigitado );
                Toast.makeText(MainActivity.this, "Texto Salvo !!!" ,Toast.LENGTH_LONG).show();
            }
        });

        //Recuperar o que foi gravado
        if( lerArquivo() != null ) {
            text.setText( lerArquivo() );
        }

    }

    private void gravarNoArquivo(String texto){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter( openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE) );
            outputStreamWriter.write( texto );
            outputStreamWriter.close();
        } catch (IOException e){
            Log.v("MainActivity", e.toString());
        }

    }

    private String lerArquivo() {
        String resultado = "";

        try {
            //abrir o arquivo
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if(arquivo!=null) {
                //ler o arquivo
                InputStreamReader inputStreamReader = new InputStreamReader( arquivo );

                //gerar buffer do arquivo
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

                //Recuperar texto do arquivo
                String linhaArquivo = "";
                while( (linhaArquivo = bufferedReader.readLine()) != null) {
                    resultado += linhaArquivo;
                }

                arquivo.close();

            }
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }



        return resultado;
    }
}
