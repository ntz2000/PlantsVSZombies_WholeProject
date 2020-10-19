package com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer;

import android.view.MotionEvent;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.ShowPlant;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.ShowZombie;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.engine.GameEngine;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.NingTianzhe181310419.plantsvszombies_wholeproject.engine.GameEngine.isStart;

public class FightLayer extends BaseLayer {
    private static final int max_hang = 7;
    private static final int max_lie = 6;
    public static final int TAG_SELECTED_BOX = 1;
    private CCTMXTiledMap map;
    private ArrayList<CGPoint>mZombiePoint;//展示的僵尸做标集合
    private CCSprite mChooseBox;//植物展示框
    private CCSprite mSelectedBox;//植物选择框
    private CCSprite start;//一起摇滚吧开始按钮
    private CCSprite startLabel;//开始游戏提示语
    private CopyOnWriteArrayList<ShowPlant> mShowPlants;//保存每个展示植物
    private CopyOnWriteArrayList<ShowPlant> mSelectedPlants = new CopyOnWriteArrayList<ShowPlant>();//保存每个已选植物
    private ArrayList<ShowZombie>mShowZombies = new ArrayList<ShowZombie>();//存放展示的僵尸
    public FightLayer() {
        loadMap();
        loadZombie();
    }

    //加载白天地图文件
    public void loadMap(){
        map = CCTMXTiledMap.tiledMap("combat/map1.tmx");//map指向地图文件
        this.addChild(map);
        mZombiePoint = CommonUtils.loadZombiePoint(map,"show");//获取开始时展现的僵尸坐标点
        moveMap();
    }

    //加载僵尸
    public  void loadZombie(){
        for(CGPoint point : mZombiePoint){//遍历所有僵尸的坐标点
            ShowZombie zombie = new ShowZombie();//创建一个CCSprite子类
            zombie.setPosition(point);//将每一个对象设置在坐标点上
            map.addChild(zombie);//加入图层
            mShowZombies.add(zombie);//加入展示僵尸的数组
        }
    }

    //移动地图
    public void moveMap(){
        CCDelayTime delayTime = CCDelayTime.action(1.5f);//开始查看地图延时
        double offset = winSize.width - map.getContentSizeRef().width;//地图移动偏移量
        CCMoveBy move = CCMoveBy.action(1.2f,ccp(offset,0));//地图移动事件
        CCSequence sequence = CCSequence.actions(delayTime, move,CCDelayTime.action(1.3f), CCCallFunc.action(this, "showPlantBox"));
        map.runAction(sequence);
    }

    //展示植物框
    public void showPlantBox(){
        setIsTouchEnabled(true);
        showSelectedBox();
        showChooseBox();
    }

    //展示已选植物框
    public void showSelectedBox(){
        mSelectedBox = CCSprite.sprite("choose/SeedBank.png");//选择植物框
        mSelectedBox.setAnchorPoint(0,1);//锚点设置在左上角
        mSelectedBox.setPosition(0,winSize.height);//位置设置在左上角
        this.addChild(mSelectedBox,0,TAG_SELECTED_BOX);
    }

    //展示选择植物框
    public  void showChooseBox(){
        mChooseBox = CCSprite.sprite("choose/SeedChooser.png");//已选植物框
        mChooseBox.setAnchorPoint(0,0);//锚点设置在左下角
        mChooseBox.setPosition(0,0);//位置设置在左下角
        this.addChild(mChooseBox);
        mShowPlants = new CopyOnWriteArrayList<ShowPlant>();//保存每个植物的坐标
        for(int i=0;i<=25;i++){
            ShowPlant showPlant = new ShowPlant(mChooseBox, i);//调用ShowPlant的构造函数获取植物图片
            mChooseBox.addChild(showPlant.getBgPlant(),2);//将该卡片添加到图层
            mChooseBox.addChild(showPlant.getShowPlant(),2);//将该卡片添加到图层
            mShowPlants.add(showPlant);//将该植物添加至数组
        }
        start = CCSprite.sprite("choose/SeedChooser_Button_Disabled.png");//默认指向灰色开始按钮图片
        start.setAnchorPoint(0.5f,0);//锚点设为中下
        start.setPosition(mChooseBox.getContentSizeRef().width/2,30);//设置在植物选择框中下位置
        mChooseBox.addChild(start);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        if(isStart){//如果游戏已经开始则不在此类中判断触摸事件
            GameEngine.getInstance().handleTouch(event);//将事件传给游戏引擎处理
            return true;
        }
        CGPoint convertTouchToNodeSpace = convertTouchToNodeSpace(event);//将安卓坐标转化为Cocos2d坐标
        //判定并执行选择植物事件
        if(CGRect.containsPoint(mChooseBox.getBoundingBox(),convertTouchToNodeSpace)){//判断点击坐标是否落在植物选择框上
            //判断是否点击开始游戏按钮
            if(CGRect.containsPoint(start.getBoundingBox(),convertTouchToNodeSpace)&&mSelectedPlants.size()>=9){//判断点击坐标是否落在开始按钮上
                gamePrepare();
                return true;
            }
            for (ShowPlant showPlant : mShowPlants){//遍历植物数组
                if(CGRect.containsPoint(showPlant.getShowPlant().getBoundingBox(), convertTouchToNodeSpace)){//判断点击坐标是否落在单个植物上
                    if(mSelectedPlants.size()<9){//最多放9个植物
                        mSelectedPlants.add(showPlant);//将已选植物加入数组
                        CCMoveTo moveTo = CCMoveTo.action(0.3f, ccp(78 + (mSelectedPlants.size() - 1) * 53,winSize.height-8));//获取坐标并将已选植物归位
                        showPlant.getShowPlant().runAction(moveTo);//将已选植物归位
                        break;
                    }
                }
            }
        }
        //判定并执行取消选择植物事件
        else if(CGRect.containsPoint(mSelectedBox.getBoundingBox(),convertTouchToNodeSpace)){//判断触碰点是否落在已选植物框
            boolean isSelected = false;//布尔变量判断一次循环该植物是否被点击
            for (ShowPlant showPlant : mSelectedPlants){//遍历已选植物数组
                if(CGRect.containsPoint(showPlant.getShowPlant().getBoundingBox(), convertTouchToNodeSpace)){//判断点击坐标是否落在已选的单个植物上
                    CCMoveTo moveTo = CCMoveTo.action(0.3f, showPlant.getBgPlant().getPosition());//将取消的植物移动回原位置
                    showPlant.getShowPlant().runAction(moveTo);//将要取消的植物归位
                    mSelectedPlants.remove(showPlant);//移除已选植物
                    isSelected = true;//植物被点击
                    continue;//一旦有植物被点击将执行继续遍历后面的植物
                }
                if (isSelected) {//说明有植物被点击
                    CCMoveBy moveBy = CCMoveBy.action(0.3f,ccp(-53,0));//之后的植物遍历并向左偏移
                    showPlant.getShowPlant().runAction(moveBy);//启动事件
                }
            }
        }
        //判断是否选满，选满则更换开始按钮图片
        if(mSelectedPlants.size() < 9){
            start = CCSprite.sprite("choose/SeedChooser_Button_Disabled.png");
        }
        else if(mSelectedPlants.size() >= 9){
            start = CCSprite.sprite("choose/SeedChooser_Button.png");
        }
        start.setAnchorPoint(0.5f,0);
        start.setPosition(mChooseBox.getContentSizeRef().width/2,30);
        mChooseBox.addChild(start);
        return super.ccTouchesBegan(event);
    }

    //准备战斗
    private void gamePrepare() {
        setIsTouchEnabled(false);
        //隐藏植物框
        mChooseBox.removeSelf();
        //地图移回原位置
        moveMapBack();
        //缩放已选植物框(可以不用)
        for (ShowPlant showPlant : mSelectedPlants){//遍历已选植物数组
            this.addChild(showPlant.getShowPlant());//将已选植物添加到战斗图层
        }
    }

    //将地图移回初始位置
    private void moveMapBack() {
        double offset = map.getContentSizeRef().width - winSize.width;//地图移动偏移量
        CCMoveBy move = CCMoveBy.action(1.2f,ccp(offset,0));//地图移动事件
        CCSequence sequence = CCSequence.actions(CCDelayTime.action(1.3f), move,CCDelayTime.action(1.3f),CCCallFunc.action(this, "showLabel"));
        map.runAction(sequence);
    }
    //展示文字
    public void showLabel(){
        //回收一开始在马路上展示的僵尸
        for (ShowZombie zombie : mShowZombies){
            zombie.removeSelf();//遍历并释放
        }
        mShowZombies.clear();//清除数组
        //显示准备开始战斗的提示语
        startLabel = CCSprite.sprite("startready/startReady_00.png");//设置默认图片
        startLabel.setPosition(winSize.width/2,winSize.height/2);//设置提示语居中
        this.addChild(startLabel);
        CCFiniteTimeAction animate = (CCFiniteTimeAction)CommonUtils.animate("startready/startReady_%02d.png",2,false,0.8f);//设置开始战斗提示语动画
        CCSequence sequence = CCSequence.actions(animate, CCCallFunc.action(this, "gameBegin"));
        startLabel.runAction(sequence);
        startLabel.runAction(animate);//播放开始战斗提示语动画
    }
    //开始游戏
    public void gameBegin(){
        setIsTouchEnabled(true);
        startLabel.removeSelf();//释放提示语
        isStart = true;
        GameEngine.getInstance().gameStart(map,mSelectedPlants);
    }
}
