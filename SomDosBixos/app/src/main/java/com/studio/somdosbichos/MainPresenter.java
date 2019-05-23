package com.studio.somdosbichos;

import android.content.Context;
import android.media.MediaPlayer;

import com.studio.somdosbichos.animals.Cao;
import com.studio.somdosbichos.animals.Gato;
import com.studio.somdosbichos.animals.Leao;
import com.studio.somdosbichos.animals.Macaco;
import com.studio.somdosbichos.animals.Ovelha;
import com.studio.somdosbichos.animals.Vaca;

class MainPresenter {
    private Cao cao = new Cao();
    private Gato gato = new Gato();
    private Leao leao = new Leao();
    private Macaco macaco = new Macaco();
    private Ovelha ovelha = new Ovelha();
    private Vaca vaca = new Vaca();


    public int getCaoId() { return cao.getId(); }

    public int getGatoId() {
        return gato.getId();
    }

    public int getLeaoId() {
        return leao.getId();
    }

    public int getMacacoId() {
        return macaco.getId();
    }

    public int getOvelhaId() {
        return ovelha.getId();
    }

    public int getVacaId() {
        return vaca.getId();
    }


    public int getCaoSound() { return cao.getSound(); }

    public int getGatoSound() {
        return gato.getSound();
    }

    public int getLeaoSound() {
        return leao.getSound();
    }

    public int getMacacoSound() {
        return macaco.getSound();
    }

    public int getOvelhaSound() {
        return ovelha.getSound();
    }

    public int getVacaSound() {
        return vaca.getSound();
    }

}
