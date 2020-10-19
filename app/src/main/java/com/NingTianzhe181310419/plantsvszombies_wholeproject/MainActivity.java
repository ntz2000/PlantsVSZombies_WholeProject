package com.NingTianzhe181310419.plantsvszombies_wholeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer.FightLayer;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer.MenuLayer;
import com.NingTianzhe181310419.plantsvszombies_wholeproject.Layer.WelcomeLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.sound.SoundEngine;

public class MainActivity extends Activity {
    private CCDirector director = CCDirector.sharedDirector();
    private SoundEngine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CCGLSurfaceView view = new CCGLSurfaceView(this);
        setContentView(view);
        director = CCDirector.sharedDirector();//获取导演单例类
        director.attachInView(view);//开启绘制线程的方法
        director.setDisplayFPS(true);//开启帧率显示
        director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);//横屏
        director.setScreenSize(1280, 768);//用于屏幕适配
        CCScene scene = CCScene.node();//创建一个场景对象
        //WelcomeLayer layer = new WelcomeLayer();//创建一个图层对象
        MenuLayer layer = new MenuLayer();
        scene.addChild(layer);//给场景添加图层
        engine = SoundEngine.sharedEngine();
        engine.playSound(CCDirector.theApp, R.raw.faster,true);
        director.runWithScene(scene);//导演运行场景
    }
    @Override
    protected void onResume() {
        super.onResume();
        director.resume();
        engine.resumeSound();
    }

    @Override
    protected void onPause() {
        super.onPause();
        director.pause();
        engine.pauseSound();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        director.end();
        System.exit(0);
    }
}