package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Obstacle implements GameObject{

    private int id;

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

    public void decrementX(double dX){
        x -= dX;
        hitBox.left -= dX;
        hitBox.right -= dX;
    }



    private Bitmap coin_spriteSheet;
    private Animation animation = new Animation();
    private long startTime;

    private int width;
    private int height;

    public Obstacle(int id, int posX, int posY, int dX, int dY){
        this.coin_spriteSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yen_coin_sheet, Constants.NO_SCALE);
        setX(posX);
        setY(posY);
        setDx(dX);
        setDy(dY);
        this.width = 32;
        this.height = 32;
        Rect hitBox = new Rect(posX,posY,posX+width,posY+height);
        setHitbox(hitBox);

        Bitmap[] images = new Bitmap[5];
        for(int i=0; i<images.length; i++){
            images[i] = Bitmap.createBitmap(coin_spriteSheet, (i * width), 0, width, height);
        }

        animation.setFrames(images);
        animation.setDelay(120);
        startTime = System.nanoTime();
    }

    public int getHeight() { return height; }

    public void update(){ animation.update(); }

    public void draw(Canvas canvas){ canvas.drawBitmap(animation.getImage(), (int)this.x, (int)this.y, null); }


    public boolean playerCollide(Player player){
        return Rect.intersects(hitBox, player.getHitbox());
    }


}
