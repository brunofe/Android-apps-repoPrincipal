package com.studio.somdosbichos.animals;

import com.studio.somdosbichos.R;

public class Gato extends Animal implements AnimalProperty {

    public Gato(){
        super(R.id.gatoId, R.raw.gato);
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
