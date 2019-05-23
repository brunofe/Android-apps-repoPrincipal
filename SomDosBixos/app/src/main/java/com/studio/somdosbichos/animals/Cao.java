package com.studio.somdosbichos.animals;

import com.studio.somdosbichos.R;

public class Cao extends Animal implements AnimalProperty {

    public Cao() {
        super(R.id.caoId, R.raw.cao);
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
