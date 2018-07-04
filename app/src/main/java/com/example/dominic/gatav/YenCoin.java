package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class YenCoin extends GameObject {
    private Bitmap spriteSheet;
    private Animation animation = new Animation();
    private long startTime;

    public YenCoin(Bitmap spriteSheet, int posX, int posY, int dX, int dY, int width, int height, int numFrames){
        this.spriteSheet = spriteSheet;
        this.x = posX;
        this.y = posY;
        this.dx = dX;
        this.dy = dY;
        this.width = width;
        this.height = height;

        Bitmap[] images = new Bitmap[numFrames];
        for(int i=0; i<images.length; i++){
            images[i] = Bitmap.createBitmap(spriteSheet, (i * width), 0, width, height);
        }

        animation.setFrames(images);
        animation.setDelay(60);
        startTime = System.nanoTime();

    }

    public void update(){ animation.update(); }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), x,y, null);
    }


}
