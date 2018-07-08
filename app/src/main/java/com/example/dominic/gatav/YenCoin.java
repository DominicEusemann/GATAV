package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class YenCoin extends Obstacle {
    private Bitmap spriteSheet;
    private Animation animation = new Animation();
    private long startTime;

    private double x;
    private double y;
    private double dx;
    private double dy;
    private int width;
    private int height;

    public YenCoin(Bitmap spriteSheet, int posX, int posY, int dX, int dY, int width, int height, int frameCount, Rect hitBox){
        this.spriteSheet = spriteSheet;
        this.x = posX;
        this.y = posY;
        this.dx = dX;
        this.dy = dY;
        this.width = width;
        this.height = height;

        super.setRectangle(hitBox);

        Bitmap[] images = new Bitmap[frameCount];
        for(int i=0; i<images.length; i++){
            images[i] = Bitmap.createBitmap(spriteSheet, (i * width), 0, width, height);
        }

        animation.setFrames(images);
        animation.setDelay(120);
        startTime = System.nanoTime();

    }

    public int getHeight() { return height; }

    public void update(){ animation.update(); }

    public void draw(Canvas canvas){ canvas.drawBitmap(animation.getImage(), (int)(x),(int)(y), null); }


}
