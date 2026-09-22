package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.Plants;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.AttackPlant;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.Bullet;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;

public class PeaShooter extends AttackPlant {
    public PeaShooter() {
        super("plant/Peashooter/Frame00.png");
        baseAction();
    }

    @Override
    public Bullet creatBullet() {
        return null;
    }

    @Override
    public void baseAction() {
        CCAction animate = CommonUtils.animate("plant/Peashooter/Frame%02d.png", 12, true,0.15f);
        this.runAction(animate);
    }
}
