package com.studio.corpesonalizada;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGrup;
    private RadioButton radioButtonSelecionado;
    private Button botaoSalvar;
    private ConstraintLayout layout;
    private static final String ARQUIVO_PREFERNCIA = "ArqPreferencia";

    private Button startLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGrup = findViewById(R.id.radioGrupId);
        botaoSalvar = findViewById(R.id.buttonSalvarId);
        layout = findViewById(R.id.layoutId);
        startLoop = findViewById(R.id.butonStartId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idRadioButtonEscolhid = radioGrup.getCheckedRadioButtonId();

                if( idRadioButtonEscolhid>0) {
                    radioButtonSelecionado = findViewById( idRadioButtonEscolhid );
                    String corEscolhida = radioButtonSelecionado.getText().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERNCIA, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("corEscolhida", corEscolhida);

                    //pra dizer que foi finalizada as ediçoes e pode gravar o arquivo
                    editor.commit();

                    setBackground( corEscolhida );

                }
            }
        });

        /********* loop color *********/

        startLoop.setOnClickListener(new View.OnClickListener() {
            int waitTime = 5000;
            Timer timer = new Timer();

            @Override
            public void onClick(View v) {
                //Color.rgb(255,0,0); a //Color.rgb(255,255,0);

                for(int i=0;i<=255;i++){
                    final int finalI = i;
                    new Handler().postDelayed(new Runnable() {@Override public void run() {
                        layout.setBackgroundColor(Color.rgb(255, finalI,0));
                    }}, waitTime);
                }
                
                //Color.rgb(255,255,0); a //Color.rgb(0,255,0);
                for(int i=255;i>=0;i--){
                    final int finalI = i;
                    new Handler().postDelayed(new Runnable() {@Override public void run() {
                        layout.setBackgroundColor(Color.rgb(finalI,255,0));
                    }}, waitTime);
                }

                ///Color.rgb(0,255,0); a //Color.rgb(0,255,255);
                for(int i=0;i<=255;i++){
                    final int finalI = i;
                    new Handler().postDelayed(new Runnable() {@Override public void run() {
                        layout.setBackgroundColor(Color.rgb(0,255,finalI));
                    }}, waitTime);
                }

                //Color.rgb(0,255,255); a //Color.rgb(0,0,255);
                for(int i=255;i>=0;i--){
                    final int finalI = i;
                    new Handler().postDelayed(new Runnable() {@Override public void run() {
                        layout.setBackgroundColor(Color.rgb(0,finalI,255));
                    }}, waitTime);
                }

                //Color.rgb(0,0,255); a //Color.rgb(255,0,255);
                for(int i=0;i<=255;i++){
                    final int finalI = i;
                    new Handler().postDelayed(new Runnable() {@Override public void run() {
                        layout.setBackgroundColor(Color.rgb(finalI,0,255));
                    }}, waitTime);
                }

                //Color.rgb(255,0,255); a //Color.rgb(255,0,0);
                for(int i=255;i>=0;i--){
                    final int finalI = i;
                    new Handler().postDelayed(new Runnable() {@Override public void run() {
                        layout.setBackgroundColor(Color.rgb(255,0,finalI));
                    }}, waitTime);
                }
            }
        });


        /********* loop color *********/

        //recuperar a cor salva
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERNCIA,Context.MODE_PRIVATE);
        if( sharedPreferences.contains("corEscolhida")){
            String corRecuperada = sharedPreferences.getString("corEscolhida", "Laranjs");
            setBackground( corRecuperada );
        }
    }

    private void setBackground(String cor){
            if(cor.equals("Azul")){
                layout.setBackgroundColor(Color.rgb(0,0,255));
            }else if(cor.equals("Laranja")) {
                layout.setBackgroundColor(Color.rgb(255,125,0));
            }else if(cor.equals("Verde")) {
                layout.setBackgroundColor(Color.rgb(0,255,0));
            }
    }


}
