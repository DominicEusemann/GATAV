package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player extends GameObject {
    private Bitmap spriteSheetRun;
    private Bitmap[] scaledSpritesRun;
    private Bitmap spriteSheetJump;
    private Bitmap[] scaledSpritesJump;
    private int score;
    private double dYa;
    private boolean playing;
    private boolean jump;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap run, /*Bitmap jump,*/ int width, int height, int numFramesRun/*, int numFramesJump*/){
        super.x = GamePanel.WIDTH/8;
        super.y = GamePanel.HEIGHT - height;
        super.dy = 0;

        this.spriteSheetRun = run ;
        //this.spriteSheetJump = jump;
        this.height = height;
        this.width = width;

        scaledSpritesRun = new Bitmap[numFramesRun];
        scaledSpritesRun[0] = Bitmap.createBitmap(spriteSheetRun, 0 * width, 0 * height , width, height);
        scaledSpritesRun[1] = Bitmap.createBitmap(spriteSheetRun, 1 * width, 0 * height , width, height);
        scaledSpritesRun[2] = Bitmap.createBitmap(spriteSheetRun, 2 * width, 0 * height , width, height);
        scaledSpritesRun[3] = Bitmap.createBitmap(spriteSheetRun, 3 * width, 0 * height , width, height);

        scaledSpritesRun[4] = Bitmap.createBitmap(spriteSheetRun, 0 * width, 1 * height , width, height);
        scaledSpritesRun[5] = Bitmap.createBitmap(spriteSheetRun, 1 * width, 1 * height , width, height);
        scaledSpritesRun[6] = Bitmap.createBitmap(spriteSheetRun, 2 * width, 1 * height , width, height);
        scaledSpritesRun[7] = Bitmap.createBitmap(spriteSheetRun, 3 * width, 1 * height , width, height);
        /*

        wieso zur hölle nicht äquivalent zu dem obendrüber????? #schleifenkannich

        int k = 0;
        for(int i=0; i<2; i++){
            for(int j=0; i<numFramesRun/2; j++){
                scaledSpritesRun[k] = Bitmap.createBitmap(spriteSheetRun, j * width, i * height , width, height);
                k++;
            }
        }
        */

        /*
        scaledSpritesJump = new Bitmap[numFramesJump];

        for(int i=0; i<scaledSpritesJump.length; i++){
            if(i == (numFramesRun/2)) spriteCutHeight = height;
            scaledSpritesJump[i] = Bitmap.createBitmap(spriteSheetJump, (i * width), spriteCutHeight , width, height);
            spriteCutHeight = 0;
        }

        */

        animation.setFrames(scaledSpritesRun);
        animation.setDelay(120);
        startTime = System.nanoTime();
    }

    @Override
    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;
        if(elapsed > 100){
            score++;
            startTime = System.nanoTime();
        }
        animation.update();
        if(jump){
            // player translation + jump animation


        }
    }

    @Override
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
