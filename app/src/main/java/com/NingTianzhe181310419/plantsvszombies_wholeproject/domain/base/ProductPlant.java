package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base;

public abstract class ProductPlant extends Plant {
    public ProductPlant(String filepath) {
        super(filepath);
    }
    //产生阳光或金币
    public abstract void create();
}
