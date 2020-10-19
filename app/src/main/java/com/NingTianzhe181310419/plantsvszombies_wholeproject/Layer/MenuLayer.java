package com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.utils.CommonUtils;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCSprite;

public class MenuLayer extends BaseLayer {
    public MenuLayer() {
        CCSprite main_Menu = CCSprite.sprite("menu/main_menu_bg.png");
        main_Menu.setAnchorPoint(0,0);
        this.addChild(main_Menu);
        //冒险模式菜单按钮
        CCMenu menu = CCMenu.menu();//初始化CCMenu
        CCSprite normalSprite = CCSprite.sprite("menu/start_adventure_default.png");//默认图片
        CCSprite selectedSprite = CCSprite.sprite("menu/start_adventure_press.png");//选中图片
        CCMenuItemSprite item = CCMenuItemSprite.item(normalSprite,selectedSprite,this,"onClickAdventure");//用一张图片初始化一个菜单选项
        menu.addChild(item);
        menu.setPosition(winSize.width/2+280,winSize.height/2+140);//设置冒险模式菜单的位置
        menu.setRotation(1.5f);
        //小游戏菜单按钮
        CCMenu survivalMenu = CCMenu.menu();
        CCSprite survivalNormal = CCSprite.sprite("menu/SelectorSurvivalButtonDefault.png");
        CCSprite survivalSelected = CCSprite.sprite("menu/SelectorSurvivalButtonSelected.png");
        CCMenuItemSprite survivalItem = CCMenuItemSprite.item(survivalNormal, survivalSelected,this,"onClickSurvival");
        survivalMenu.addChild(survivalItem);
        survivalMenu.setPosition(winSize.width/2+650,winSize.height/2+175);//设置冒险模式菜单的位置
        survivalMenu.setRotation(1.5f);
        survivalMenu.setScale(1.6f);
        this.addChild(menu);
        this.addChild(survivalMenu);
    }

    //冒险模式按钮点击事件
    public void onClickAdventure(Object object){//因为必须要传一个东西才能响应点击事件
        CommonUtils.changeLayer(new FightLayer());
    }

    //迷你小游戏按钮点击事件
    public void onClickSurvival(Object object){

    }
}
