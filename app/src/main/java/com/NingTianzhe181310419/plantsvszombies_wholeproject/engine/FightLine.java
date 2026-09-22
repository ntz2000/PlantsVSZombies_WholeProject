package com.NingTianzhe181310419.plantsvszombies_wholeproject.engine;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.Plant;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.Zombie;

import org.cocos2d.actions.CCScheduler;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FightLine {
    private HashMap<Integer, Plant> mPlants = new HashMap<Integer, Plant>();//key表示植物在第几列
    private CopyOnWriteArrayList<Zombie> mZombies = new CopyOnWriteArrayList<Zombie>();//表示该战线上所有僵尸
    public FightLine(int i) {
        CCScheduler scheduler = CCScheduler.sharedScheduler();
        scheduler.schedule("attackPlant",this,0.2f,false);
    }
    //僵尸攻击植物
    public void attackPlant(float f){

    }
    //添加植物
    public void addPlant(Plant plant){
        mPlants.put(plant.getColumn(),plant);
    }
    //添加僵尸
    public void addZombie(Zombie zombie){
        mZombies.add(zombie);
    }
    //判断战线上是否有植物，有的话就不能再安放了
    public boolean containsPlant(Plant plant){
        return mPlants.keySet().contains(plant.getColumn());//在列键值中查找是否包含
    }

}
