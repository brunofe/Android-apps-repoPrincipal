package com.studio.corpesonalizada;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
            int waitTime = 4;

            @Override
            public void onClick(View v) {
                startLoop.setEnabled(false);
                //Color.rgb(255,0,0); a //Color.rgb(255,255,0);
                final Thread thread1= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count = 0;
                        for(int i=0;i<=255;i++){
                            pauseThread(waitTime);
                            layout.setBackgroundColor(Color.rgb(255, i,0));
                            Log.d("TAG", "colors: init "+count++);
                        }
                    }
                });

                //Color.rgb(255,255,0); a //Color.rgb(0,255,0);
                final Thread thread2= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=255;i>=0;i--){
                            pauseThread(waitTime);
                            layout.setBackgroundColor(Color.rgb(i,255,0));
                        }
                    }
                });

                ///Color.rgb(0,255,0); a //Color.rgb(0,255,255);
                final Thread thread3= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<=255;i++){
                            pauseThread(waitTime);
                            layout.setBackgroundColor(Color.rgb(0,255,i));
                        }
                    }
                });

                //Color.rgb(0,255,255); a //Color.rgb(0,0,255);
                final Thread thread4= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=255;i>=0;i--){
                            pauseThread(waitTime);
                            layout.setBackgroundColor(Color.rgb(0,i,255));
                        }
                    }
                });

                //Color.rgb(0,0,255); a //Color.rgb(255,0,255);
                final Thread thread5= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<=255;i++){
                            pauseThread(waitTime);
                            layout.setBackgroundColor(Color.rgb(i,0,255));
                        }
                    }
                });

                //Color.rgb(255,0,255); a //Color.rgb(255,0,0);
                final Thread thread6= new Thread(new Runnable() {
                    @Override

                    public void run() {
                        int count =0;
                        for(int i=255;i>=0;i--){
                            pauseThread(waitTime);
                            layout.setBackgroundColor(Color.rgb(255,0,i));
                            Log.d("TAG", "colors: final "+count++);
                        }
                    }
                });



                /**** START ***/
                int time = 0;
                int increment=1300;

                thread1.start();

                time+=increment;
                final int sleepTime2=time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thread2.start();
                    }
                },sleepTime2);

                time+=increment;
                final int sleepTime3=time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thread3.start();
                    }
                },sleepTime3);

                time+=increment;
                final int sleepTime4=time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thread4.start();
                    }
                },sleepTime4);

                time+=increment;
                final int sleepTime5=time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thread5.start();
                    }
                },sleepTime5);

                time+=increment;
                final int sleepTime6=time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thread6.start();
                    }
                },sleepTime6);

                time+=increment;
                final int sleepTimeFinal=time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TAG","colors: bott ");
                        startLoop.setEnabled(true);
                    }
                },sleepTimeFinal);

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

    private void setBackground(String cor)
    {
        if(cor.equals("Azul")){
            layout.setBackgroundColor(Color.rgb(0,0,255));
        }else if(cor.equals("Laranja")) {
            layout.setBackgroundColor(Color.rgb(255,125,0));
        }else if(cor.equals("Verde")) {
            layout.setBackgroundColor(Color.rgb(0,255,0));
        }
    }

    public void pauseThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}