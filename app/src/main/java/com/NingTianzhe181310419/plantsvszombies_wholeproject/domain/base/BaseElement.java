package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base;

import org.cocos2d.nodes.CCSprite;

public abstract class BaseElement extends CCSprite {
    //初始化精灵图片
    public BaseElement(String filepath) {
        super(filepath);
    }
    //基本摇摆动画
    public abstract void baseAction();
    //释放内存
    public void destroy(){
        removeSelf();
    }
}
