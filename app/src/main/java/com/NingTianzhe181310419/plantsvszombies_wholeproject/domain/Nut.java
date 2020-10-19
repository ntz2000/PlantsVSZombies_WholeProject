package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.DefancePlant;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;

public class Nut extends DefancePlant {
    public Nut() {
        super("plant/WallNut/high/Frame00.png");
        baseAction();
    }

    @Override
    public void baseAction() {
        CCAction animate = CommonUtils.animate("plant/WallNut/high/Frame%02d.png", 15, true);
        this.runAction(animate);
    }
}
