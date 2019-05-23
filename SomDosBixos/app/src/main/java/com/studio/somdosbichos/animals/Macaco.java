package com.studio.somdosbichos.animals;

import com.studio.somdosbichos.R;

public class Macaco extends Animal implements AnimalProperty {

    public Macaco(){
        super(R.id.macacoId, R.raw.macaco);
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
