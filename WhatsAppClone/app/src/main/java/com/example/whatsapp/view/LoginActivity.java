package com.example.whatsapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Random;


public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                String telefoneSemFormatacao = telefoneCompleto.replace("+","");
                telefoneSemFormatacao = telefoneCompleto.replace("-","");

                //gerar token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt( 9999 - 1000 ) + 1000;
                String token = String.valueOf( numeroRandomico );



            }
        });
    }
}
