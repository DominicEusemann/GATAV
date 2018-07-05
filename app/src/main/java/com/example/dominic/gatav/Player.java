package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player extends GameObject {
    private Bitmap spriteSheet;
    private int score;
    private double dYa;
    private boolean playing;
    private boolean jump;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int width, int height, int numFrames){
        super.x = 100;
        super.y = GamePanel.HEIGHT/2;
        super.dy = 0;

        this.spriteSheet = res;
        this.height = height;
        this.width = width;

        Bitmap[] images = new Bitmap[numFrames];
        for(int i=0; i<images.length-1; i++){
            images[i] = Bitmap.createBitmap(spriteSheet, (i * width), 0, width, height);
        }

        animation.setFrames(images);
        animation.setDelay(30);
        startTime = System.nanoTime();
    }


    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;
        if(elapsed > 100){
            score++;
            startTime = System.nanoTime();
        }
        animation.update();
        if(jump){
            //TODO: player translation + jump animation

        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), x,y, null);
    }

    public int getScore() { return this.score; }
    public boolean isPlaying() { return playing; }

    public void setPlaying(boolean playing) { this.playing = playing; }
    public void setJump(boolean b) { jump = b; }

    public void resetDYA() { this.dYa = 0; }
    public void resteScore() { this.score = 0; }

}
