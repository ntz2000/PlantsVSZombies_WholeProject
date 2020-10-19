package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain;

import android.widget.Button;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;

public class ShowPlant extends CCSprite {
    private int id;//标识植物
    private static final int max_hang = 7;
    private static final int max_lie = 6;
    private CCSprite bgPlant;//初始化背景半透明图片
    private CCSprite showPlant;//初始化要展现的图片
    public ShowPlant(CCSprite mChooseBox,int i) {//i表示植物的序号
        this.id = i;
        String format = "choose/p%02d.png";
        //对要背景虚化图片的一波操作
        bgPlant = CCSprite.sprite(String.format(format, i));//初始化背景图片
        //sprite.setScale(1.2);//设置植物卡片的大小
        bgPlant.setAnchorPoint(0,1);//设置植物卡片的锚点
        double w,h;
        w = mChooseBox.getContentSizeRef().width;//获取展示植物框的宽度
        h = mChooseBox.getContentSizeRef().height;//获取展示植物框的高度
        int hang = i / max_hang +1;//获取该植物卡片所在行
        int lie = i % max_hang +1;//获取该植物卡片所在列
        float x = (float) ((lie - 1) * (w - 50) / max_hang + 40);//设置该植物卡片的横坐标
        float y = (float) (h - (hang - 1) * (h - 50 * 2) / max_lie - 60);//设置该植物卡片的纵坐标
        bgPlant.setPosition(x,y);//设置植物卡片坐标
        bgPlant.setOpacity(100);//设置半透明
        //对要展现图片的一波操作
        showPlant = CCSprite.sprite(String.format(format, i));//初始化展现图片
        showPlant.setAnchorPoint(0,1);//锚点设置为原点
        showPlant.setPosition(bgPlant.getPosition());
    }
    //获得bgPlant
    public  CCSprite getBgPlant(){
        return bgPlant;
    }
    //获得showPlant
    public  CCSprite getShowPlant(){
        return showPlant;
    }

    public int getId(){
        return id;
    }
}
