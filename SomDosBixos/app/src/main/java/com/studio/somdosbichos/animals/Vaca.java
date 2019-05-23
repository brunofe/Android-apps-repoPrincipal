package com.studio.somdosbichos.animals;

import com.studio.somdosbichos.R;

public class Vaca extends Animal implements AnimalProperty {

    public Vaca(){
        super(R.id.vacaId, R.raw.vaca);
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
