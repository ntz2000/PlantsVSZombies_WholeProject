package com.NingTianzhe181310419.plantsvszombies_wholeproject.utils;

import org.cocos2d.types.CGPoint;

//确定点击事件在哪一格
public class Locale {
    static final int x0 = 320;
    static final int x1 = 425;
    static final int x2 = 523;
    static final int x3 = 638;
    static final int x4 = 735;
    static final int x5 = 840;
    static final int x6 = 940;
    static final int x7 = 1048;
    static final int x8 = 1145;
    static final int x9 = 1270;
    static final int y0 = 768 - 725;
    static final int y1 = 768 - 600;
    static final int y2 = 768 - 483;
    static final int y3 = 768 - 350;
    static final int y4 = 768 - 225;
    static final int y5 = 768 - 102;
    public static int localeColumn(CGPoint point){
        int x = (int) point.x;
        int column = 0;
        if(x>=x0&&x<=x1)
            column = 0;
        else if(x>=x1&&x<=x2)
            column = 1;
        else if(x>=x2&&x<=x3)
            column = 2;
        else if(x>=x3&&x<=x4)
            column = 3;
        else if(x>=x4&&x<=x5)
            column = 4;
        else if(x>=x5&&x<=x6)
            column = 5;
        else if(x>=x6&&x<=x7)
            column = 6;
        else if(x>=x7&&x<=x8)
            column = 7;
        else if(x>=x8&&x<=x9)
            column = 8;
        return column;
    }
    public  static int localeLine(CGPoint point){
        int y = (int) point.y;
        int line = 0;
        if(y>=y0&&y<=y1)
            line = 4;
        else if(y>=y1&&y<=y2)
            line = 3;
        else if(y>=y2&&y<=y3)
            line = 2;
        else if(y>=y3&&y<=y4)
            line = 1;
        else if(y>=y4&&y<=y5)
            line = 0;
        return line;
    }
}
