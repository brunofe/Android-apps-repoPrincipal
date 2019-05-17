package com.studio.qualserie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private ImageView imagemExibicao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBarId);
        imagemExibicao = findViewById(R.id.imagemExibicaoId);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int valorProgresso = progress;

                if(valorProgresso == 0){
                    imagemExibicao.setImageResource( R.drawable.pouco );
                } else if(valorProgresso == 1){
                    imagemExibicao.setImageResource( R.drawable.medio);
                } else if(valorProgresso == 2){
                    imagemExibicao.setImageResource( R.drawable.muito );
                } else if(valorProgresso == 3){
                    imagemExibicao.setImageResource( R.drawable.susto );
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
