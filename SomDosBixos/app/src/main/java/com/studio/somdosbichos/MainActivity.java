package com.studio.somdosbichos;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener{

    private MediaPlayer mediaPlayer;

    private MainPresenter pres = new MainPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView cao = findViewById(pres.getCaoId());
        ImageView gato = findViewById(pres.getGatoId());
        ImageView leao = findViewById(pres.getLeaoId());
        ImageView macaco = findViewById(pres.getMacacoId());
        ImageView ovelha = findViewById(pres.getOvelhaId());
        ImageView vaca = findViewById(pres.getVacaId());

        cao.setOnClickListener(this);
        gato.setOnClickListener(this);
        leao.setOnClickListener(this);
        macaco.setOnClickListener(this);
        ovelha.setOnClickListener(this);
        vaca.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.caoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, pres.getCaoSound());
                tocarSom();
                break;
            case R.id.gatoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, pres.getGatoSound());
                tocarSom();
                break;

            case R.id.leaoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, pres.getLeaoSound());
                tocarSom();
                break;

            case R.id.macacoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, pres.getMacacoSound());
                tocarSom();
                break;

            case R.id.ovelhaId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, pres.getOvelhaSound());
                tocarSom();
                break;

            case R.id.vacaId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, pres.getVacaSound());
                tocarSom();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public void tocarSom(){
        if(mediaPlayer != null)  {
            mediaPlayer.start();
        }
    }
}
