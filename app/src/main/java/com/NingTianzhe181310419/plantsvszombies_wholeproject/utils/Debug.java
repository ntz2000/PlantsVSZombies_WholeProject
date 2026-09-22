package com.NingTianzhe181310419.plantsvszombies_wholeproject.utils;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;

public class Debug {
    public static CCLabel debug(String s){
        CCLabel ccLabel = CCLabel.makeLabel(s,"宋体",100);
        ccLabel.setPosition(CCDirector.sharedDirector().winSize().width/2,CCDirector.sharedDirector().winSize().height/2);
        CCFadeOut ccFadeOut = CCFadeOut.action(2);
        ccLabel.runAction(ccFadeOut);
        return ccLabel;
    }
}
