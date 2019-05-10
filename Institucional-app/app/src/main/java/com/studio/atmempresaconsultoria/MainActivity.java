package com.studio.atmempresaconsultoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView botaoEmpresa;
    private ImageView botaoServico;
    private ImageView botaoCliente;
    private ImageView botaoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEmpresa = findViewById(R.id.empresa_id);
        botaoServico = findViewById(R.id.servicos_id);
        botaoCliente = findViewById(R.id.clientes_id);
        botaoContato = findViewById(R.id.contato_id);

        botaoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmpresaActivity.class));
            }
        });

        botaoServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServicoActivity.class));
            }
        });

        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClienteActivity.class));
            }
        });

        botaoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContatoActivity.class));
            }
        });
    }
}
