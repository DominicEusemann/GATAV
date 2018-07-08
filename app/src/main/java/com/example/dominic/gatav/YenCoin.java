package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class YenCoin extends Obstacle {
    private Bitmap spriteSheet;
    private Animation animation = new Animation();
    private long startTime;

    private int width;
    private int height;

    public YenCoin(Bitmap spriteSheet, int posX, int posY, int dX, int dY, int width, int height, int frameCount, Rect hitBox){
        this.spriteSheet = spriteSheet;
        setX(posX);
        setY(posY);
        setDx(dX);
        setDy(dY);
        this.width = width;
        this.height = height;

        super.setHitbox(hitBox);

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

    public void draw(Canvas canvas){ canvas.drawBitmap(animation.getImage(), (int)(getX()),(int)(getY()), null); }


}
