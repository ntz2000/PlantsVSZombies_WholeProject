package com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGSize;

public class BaseLayer extends CCLayer {
    public CGSize winSize = CCDirector.sharedDirector().winSize();
    public BaseLayer() {

    }
}
