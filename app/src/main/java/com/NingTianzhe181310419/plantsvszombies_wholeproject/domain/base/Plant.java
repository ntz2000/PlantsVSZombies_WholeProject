package com.NingTianzhe181310419.plantsvszombies_wholeproject.domain.base;

public abstract class Plant extends BaseElement {
    protected int life = 100;//植物生命
    protected int line;//植物行号
    protected int column;//植物列号
    public Plant(String filepath) {
        super(filepath);
        setAnchorPoint(0.5f,0);//将锚点设置在两腿中间
    }

    //被攻击
    public void attacked(int attack){
        life -= attack;//掉血
        if(life<=0){//判断血量是否为0
            destroy();//销毁
        }
    }

    public int getLine(){
        return line;
    }

    public void setLine(int line){
        this.line = line;
    }

    public int getColumn(){
        return column;
    }

    public void setColumn(int i){
        this.column = column;
    }
}
