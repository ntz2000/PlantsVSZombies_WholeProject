package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AttackPlant extends Plant {
    //弹夹
    List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();

    public AttackPlant(String filepath) {
        super(filepath);
    }
    //产生用于共计的子弹
    public abstract Bullet creatBullet();
    public List<Bullet> getBullets(){
        return bullets;
    }
}
