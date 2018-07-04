package com.example.dominic.gatav;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getDX() { return dx;}
    public int getDY() { return dy;}


    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }



}
