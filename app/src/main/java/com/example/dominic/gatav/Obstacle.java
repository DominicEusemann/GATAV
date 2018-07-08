package com.example.dominic.gatav;

import android.graphics.Rect;

public abstract class Obstacle implements GameObject{
    private double x;
    private double y;
    private double dx;
    private double dy;
    private Rect hitBox;

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }

    public double getX() { return x; }
    public double getY() { return y; }

    public double getDx() { return dx; }
    public double getDy() { return dy; }

    public void setHitbox(Rect hitBox){ this.hitBox = hitBox; }
    public Rect getHitbox(){ return hitBox; }

    public void incrementX(double dX){
        x += dX;
        hitBox.left += dX;
        hitBox.right += dX;
    }

    public boolean playerCollide(Player player){
        return Rect.intersects(hitBox, player.getHitbox());
    }
}
