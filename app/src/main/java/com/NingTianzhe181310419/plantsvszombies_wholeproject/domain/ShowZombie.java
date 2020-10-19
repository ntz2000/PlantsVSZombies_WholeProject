package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;

public class ShowZombie extends CCSprite {
    public ShowZombie() {
        sprite("zombies/zombies_1/shake/Frame00.png");//选择初始图片
        setAnchorPoint(0.5f,0);
        CCAction animate = CommonUtils.animate("zombies/zombies_1/shake/Frame%02d.png", 1, true);//加载颤抖帧动画
        runAction(animate);
    }
}
