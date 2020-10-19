package com.NingTianzhe181310419.plantsvszombies_wholeproject.utils;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer.MenuLayer;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.HashMap;

import static org.cocos2d.nodes.CCNode.ccp;

public class CommonUtils {
    //播放一帧一帧的图片形成动画
    public static CCAction animate(String format,int num,boolean repeat){
        ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
        for(int i=0;i<=num;i++){
            frames.add(CCSprite.sprite(String.format(format, i)).displayedFrame());
        }
        CCAnimation ccAnimation = CCAnimation.animation("loading",0.2f,frames);
        if(!repeat){
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation,false);
            return ccAnimate;
        }
        else{
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation);
            CCRepeatForever repeatForever = CCRepeatForever.action(ccAnimate);
            return repeatForever;
        }
    }

    //重载播放一帧一帧的图片形成动画，可设置时间间隔
    public static CCAction animate(String format,int num,boolean repeat,float t){
        ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
        for(int i=0;i<=num;i++){
            frames.add(CCSprite.sprite(String.format(format, i)).displayedFrame());
        }
        CCAnimation ccAnimation = CCAnimation.animation("loading",t,frames);
        if(!repeat){
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation,false);
            return ccAnimate;
        }
        else{
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation);
            CCRepeatForever repeatForever = CCRepeatForever.action(ccAnimate);
            return repeatForever;
        }
    }

    //更换场景，并通过淡入动画进入
    public static void changeLayer(CCLayer layer){
        CCScene scene = CCScene.node();
        scene.addChild(layer);
        CCFadeTransition fadeTransition = CCFadeTransition.transition(1, scene);//切换界面时的动画效果
        CCDirector.sharedDirector().replaceScene(fadeTransition);//表示导演切换场景进入菜单界面
    }

    //加载所选对象的坐标点
    public static ArrayList<CGPoint> loadZombiePoint(CCTMXTiledMap map,String groupName){
        ArrayList<CGPoint>points = new ArrayList<CGPoint>();
        CCTMXObjectGroup objectGroupNamed = map.objectGroupNamed(groupName);//获取对象坐标点
        ArrayList<HashMap<String,String>> objects = objectGroupNamed.objects;//将坐标导入数组
        for(HashMap<String,String> hashMap : objects){
            Integer x = Integer.parseInt(hashMap.get("x"));
            Integer y = Integer.parseInt(hashMap.get("y"));
            points.add(ccp(x,y));//将坐标传给输出的数组
        }
        return points;
    }
}
