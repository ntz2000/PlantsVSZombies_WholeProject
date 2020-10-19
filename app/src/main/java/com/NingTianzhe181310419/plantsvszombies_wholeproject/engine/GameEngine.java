package com.NingTianzhe181310419.plantsvszombies_wholeproject.engine;

import android.view.MotionEvent;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.Nut;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.PrimaryZombie;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.ShowPlant;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base.Plant;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameEngine {
    private static GameEngine mInstance = new GameEngine();
    public static boolean isStart = false;//标记游戏是否正式开始
    CCTMXTiledMap map;
    CopyOnWriteArrayList<ShowPlant> mSelectedPlants;
    ArrayList<CGPoint>mZombiePoints;
    Plant mPlant;
    public GameEngine(){

    }

    public static GameEngine getInstance(){
        return mInstance;
    }

    //游戏开始
    public void gameStart(CCTMXTiledMap map, CopyOnWriteArrayList<ShowPlant> mSelectedPlants){
        this.map = map;
        this.mSelectedPlants = mSelectedPlants;
        mZombiePoints = CommonUtils.loadZombiePoint(map, "path");//获取僵尸的移动路径
        CCScheduler scheduler = CCScheduler.sharedScheduler();
        scheduler.schedule("loadZombie", this, 5, false);//每隔五秒加载一个普通僵尸
        loadPlantPoints();
    }

    private CGPoint[][] mPlantPoints = new CGPoint[5][9];
    private void loadPlantPoints() {//加载植物的坐标点
        //初始化二维数组
        String format = "tower%d";
        for(int i=0;i<5;i++){
            ArrayList<CGPoint>loadPoint = CommonUtils.loadZombiePoint(map, String.format(format, i));
            for(int j=0;j<loadPoint.size();j++){
                mPlantPoints[4 - i][j] = loadPoint.get(j);
            }
        }

    }

    //加载僵尸
    public void loadZombie(float f) {
        Random random = new Random();
        int line = random.nextInt(5);
        CGPoint startPoint = mZombiePoints.get(line * 2);//随机抽取一行放僵尸获取起点坐标
        CGPoint endPoint = mZombiePoints.get(line * 2 + 1);//随机抽取一行放僵尸获取终点坐标
        PrimaryZombie primaryZombie = new PrimaryZombie(startPoint, endPoint);
        map.addChild(primaryZombie);
    }

    private ShowPlant mShowPlant;//当前已选植物
    //游戏开始时的触摸事件处理
    public void handleTouch(MotionEvent event){
        CGPoint convertToNodeSpace = map.convertTouchToNodeSpace(event);
        CCSprite selectedBox = (CCSprite) map.getParent().getChildByTag(1);//通过标签拿到盒子
        if(CGRect.containsPoint(selectedBox.getBoundingBox(), convertToNodeSpace)){//已选框被点击
            for (ShowPlant showPlant : mSelectedPlants){
                if(CGRect.containsPoint(showPlant.getShowPlant().getBoundingBox(), convertToNodeSpace)){//单个植物被点击
                    if(mShowPlant!=null){
                        mShowPlant.getShowPlant().setOpacity(255);
                    }
                    mShowPlant = showPlant;
                    showPlant.getShowPlant().setOpacity(100);//选择后变为半透明
                    switch (mShowPlant.getId()){
                        case 3://坚果墙
                            mPlant = new Nut();

                            break;
                    }
                    break;
                }
            }
        }
        else{//点击坐标落在草坪上
            if(isInGrass(convertToNodeSpace)){//当落在草坪上时
                if(mPlant!=null&&mShowPlant!=null){
                    map.addChild(mPlant);
                    mShowPlant.getShowPlant().setOpacity(255);
                    mPlant = null;
                    mShowPlant = null;
                }
            }
        }
    }
    //判断是否在草坪上并设置好植物位置
    public boolean isInGrass(CGPoint point){
        int column = (int) point.x / 128;//点击的格子列数
        int line = (int) (CCDirector.sharedDirector().winSize().height - point.y) / 128;//点击的格子行数
        if(line >= 1 && line <= 5 && column >= 2 && column <= 9){
            if(mPlant!=null){
                mPlant.setLine(line - 1);
                //mPlant.setColumn(column - 1);
                if(column>=2&&column<=6){
                    mPlant.setColumn(column - 2);
                }
                else{
                    mPlant.setColumn(column - 1);
                }
                mPlant.setPosition(mPlantPoints[line - 1][column - 2]);
                return true;
            }
        }
        return false;
    }
}
