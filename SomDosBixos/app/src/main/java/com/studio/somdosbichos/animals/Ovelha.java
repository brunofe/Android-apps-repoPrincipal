package com.studio.somdosbichos.animals;

import com.studio.somdosbichos.R;

public class Ovelha extends Animal implements AnimalProperty {

    public Ovelha(){
        super(R.id.ovelhaId, R.raw.ovelha);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getSound() {
        return sound;
    }
}
