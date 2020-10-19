package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base;

public abstract class DefancePlant extends Plant {
    public DefancePlant(String filepath) {
        super(filepath);
        life = 200;
    }
}
