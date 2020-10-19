package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.BaseElement;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.Zombie;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

public class PrimaryZombie extends Zombie {
    public PrimaryZombie(CGPoint startPoint,CGPoint endPoint) {
        super("zombies/zombies_1/walk/Frame00.png");
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.setPosition(startPoint);//设置僵尸起点坐标
        move();
    }

    @Override
    public void move() {
        CCMoveTo moveTo = CCMoveTo.action(CGPointUtil.distance(startPoint, endPoint)/speed, endPoint);//从起点以speed的速度移动到终点
        CCSequence sequence = CCSequence.actions(moveTo, CCCallFunc.action(this,"destroy"));
        this.runAction(sequence);
        baseAction();
    }

    @Override
    public void attack(BaseElement baseElement) {

    }

    @Override
    public void attacked(int attack) {

    }

    @Override
    public void baseAction() {
        CCAction animate = CommonUtils.animate("zombies/zombies_1/walk/Frame%02d.png", 21, true);//重复播放僵尸的行走动画
        this.runAction(animate);
    }
}
