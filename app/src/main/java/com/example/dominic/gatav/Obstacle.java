package com.example.dominic.gatav;

import android.graphics.Rect;

public abstract class Obstacle implements GameObject{

    private Rect hitBox;

    public Rect getRectangle(){ return hitBox; }
    public void setRectangle(Rect hitBox){ this.hitBox = hitBox; }

}
