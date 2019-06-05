package com.example.whatsapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View{
    MainActivityPresenter presenter;
    private Button buttonEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        buttonEnviar = findViewById(R.id.buttonEnviarId);

        //simular entrada usuario hardcoded
        final String nome = "Bruno";
        final String atributo = "altura";
        final String valor = "1,75";

        buttonEnviar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.updateUserInfo(nome,atributo,valor);
            }
        });
    }
    
    @Override
    public void showToast(String msg) {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
