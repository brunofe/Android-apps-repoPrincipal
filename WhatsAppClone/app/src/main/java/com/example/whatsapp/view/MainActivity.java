package com.example.whatsapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.repository.FireBase;

public class MainActivity extends AppCompatActivity {

    private FireBase fireBase;
    private Button botaoSair;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fireBase = ConfiguracaoFirebase.getFirebase();

    }
}
