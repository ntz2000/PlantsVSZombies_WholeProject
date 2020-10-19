package com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer;

import android.view.MotionEvent;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTRTransition;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCJumpZoomTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class WelcomeLayer extends BaseLayer{
    private CCSprite logo1,logo2;
    private CCSprite start;
    public WelcomeLayer() {
        logo1 = CCSprite.sprite("logo/DHU.png");//初始化东华大学logo
        logo1.setPosition(winSize.width/2,winSize.height/2);//使logo居中
        logo2 = CCSprite.sprite("logo/PopCap.png");//初始化宝开logo
        logo2.setPosition(winSize.width/2,winSize.height/2);//使logo居中
        logo1.setScale(1.5f);
        logo2.setScale(2);
        this.addChild(logo1);
        this.addChild(logo2);
        CCHide hide = CCHide.action();
        CCDelayTime delayTime = CCDelayTime.action(1.5f);//延时1.5秒
        CCShow show = CCShow.action();
        CCFadeIn fadeIn = CCFadeIn.action(1.5f);
        CCFadeOut fadeOut = CCFadeOut.action(2);
        CCSequence s1 = CCSequence.actions(hide,delayTime,show,fadeIn,CCDelayTime.action(0.5f),fadeOut);//logo1的淡入淡出动画
        CCSequence s2 = CCSequence.actions(hide,CCDelayTime.action(5.7f),show,fadeIn,CCDelayTime.action(0.5f),fadeOut,CCDelayTime.action(0.5f),CCCallFunc.action(this,"showWelcome"));//logo2的淡入淡出动画
        logo1.runAction(s1);
        logo2.runAction(s2);
        //setIsTouchEnabled(true);//设置触摸事件
    }

    public void showWelcome(){
        logo1.removeSelf();//释放内存
        logo2.removeSelf();//释放内存
        loadingAnim();
        start = CCSprite.sprite("cg/loading_10.png");//开始按钮精灵
        start.setPosition(winSize.width/2,80);
        start.setScale(3);
        CCHide hide = CCHide.action();
        start.runAction(hide);
        setIsTouchEnabled(true);//设置触摸事件
        this.addChild(start,2);
    }
    
    public void loadingAnim(){
        CCSprite sprite = CCSprite.sprite("cg/cg00.png");
        sprite.setAnchorPoint(0,0);
        sprite.setPosition(0,0);
        CCAction ccAnimate = CommonUtils.animate("cg/cg%02d.png", 18, false);
        sprite.runAction(ccAnimate);
        this.addChild(sprite);
    }

    public boolean ccTouchesBegan(MotionEvent event){
        CGPoint convertTouchToNodeSpace = convertTouchToNodeSpace(event);//将安卓坐标转化为Cocos2d坐标
        if(CGRect.containsPoint(start.getBoundingBox(),convertTouchToNodeSpace)){//判断是否点击了开始按钮（还不够精确，待完善）
            CommonUtils.changeLayer(new MenuLayer());
        }
        return super.ccTouchesBegan(event);
    }
}
