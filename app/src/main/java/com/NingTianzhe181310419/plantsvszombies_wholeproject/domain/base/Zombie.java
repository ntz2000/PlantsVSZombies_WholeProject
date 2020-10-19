package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base;

import org.cocos2d.types.CGPoint;

public abstract class Zombie extends BaseElement{
    protected int life = 50;//生命
    protected int attack = 10;//攻击力
    protected int speed = 20;//速度
    protected boolean isAttacking;//标记僵尸是否开始攻击

    protected CGPoint startPoint;//起点
    protected CGPoint endPoint;//终点
    public Zombie(String filepath) {
        super(filepath);
        setAnchorPoint(0.5f,0);
    }
    //移动
    public abstract void move();
    //攻击植物
    public abstract void attack(BaseElement baseElement);
    //被攻击
    public abstract void attacked(int attack);
    public boolean isAttacking(){
        return isAttacking;
    }
    public void setAttacking(boolean isAttacking){
        this.isAttacking = isAttacking;
    }
}
