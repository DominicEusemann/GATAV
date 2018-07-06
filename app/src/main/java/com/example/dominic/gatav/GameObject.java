package com.example.dominic.gatav;

import android.graphics.Canvas;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected int width;
    protected int height;

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public double getDX() { return dx;}
    public double getDY() { return dy;}


    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void update(){};
    public void draw(Canvas canvas){};

}
