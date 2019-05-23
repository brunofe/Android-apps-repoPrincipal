package com.studio.somdosbichos.animals;

import com.studio.somdosbichos.R;

public class Leao extends Animal implements AnimalProperty {

    public Leao(){
        super(R.id.leaoId, R.raw.leao);
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
