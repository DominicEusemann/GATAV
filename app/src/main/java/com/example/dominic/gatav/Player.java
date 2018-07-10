package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player implements GameObject {

    private int standard_dy = -125; //has to be <0

    private Bitmap spriteSheetRun;
    private Bitmap[] scaledSpritesRun;
    private Bitmap spriteSheetJump;
    private Bitmap[] scaledSpritesJump;
    private int score;
    private double dYa;
    private boolean playing;
    private static boolean jump;
    private Animation animation = new Animation();
    private long startTime;

    private double playerGravity = 100; //has to be >0
    private double dt = 0;
    private double currentTime = 0;
    private double preTime = 0;

    private double x;
    private double y;
    private double dx;
    private double dy;
    private int width;
    private int height;

    private Rect hitBox;

    public Player(Bitmap run, int width, int height, int numFramesRun){

        System.out.print("Width:" + width);  //<---- wtf why

        this.x = Constants.SCREEN_WIDTH/8;
        this.y = Constants.SCREEN_HEIGHT - height;
        this.dy = standard_dy;

        this.spriteSheetRun = run ;
        //this.spriteSheetJump = jump;
        this.height = height;
        this.width = width;

        hitBox = new Rect((int)x,(int)y,(int)x+width,(int)y+height);

        System.out.print(" \n  Etwas spätere Width:" + width);  //<---- wtf why

        scaledSpritesRun = new Bitmap[numFramesRun];
        /*
        scaledSpritesRun[0] = Bitmap.createBitmap(spriteSheetRun, 0 * width, 0 * height , width, height);
        System.out.print(" \n  Späte Width:" + width);
        scaledSpritesRun[1] = Bitmap.createBitmap(spriteSheetRun, 1 * width, 0 * height , width, height);
        scaledSpritesRun[2] = Bitmap.createBitmap(spriteSheetRun, 2 * width, 0 * height , width, height);
        scaledSpritesRun[3] = Bitmap.createBitmap(spriteSheetRun, 3 * width, 0 * height , width, height);
        System.out.print(" \n  Noch spätere Width:" + width);
        scaledSpritesRun[4] = Bitmap.createBitmap(spriteSheetRun, 0 * width, 1 * height , width, height);
        scaledSpritesRun[5] = Bitmap.createBitmap(spriteSheetRun, 1 * width, 1 * height , width, height);
        scaledSpritesRun[6] = Bitmap.createBitmap(spriteSheetRun, 2 * width, 1 * height , width, height);
        scaledSpritesRun[7] = Bitmap.createBitmap(spriteSheetRun, 3 * width, 1 * height , width, height);
        */
        //wieso zur hölle nicht äquivalent zu dem obendrüber????? #schleifenkannich


        int k = 0;

        for(int i=0; i<2; i++){
            for(int j=0; j<numFramesRun/2; j++){
                scaledSpritesRun[k] = Bitmap.createBitmap(spriteSheetRun, j * width, i * height , width, height);
                k++;
            }
        }


        System.out.print(" \n  Späteste Width:" + width);  //<---- why Captain Bender why?

        /*
        scaledSpritesJump = new Bitmap[numFramesJump];
        for(int i=0; i<scaledSpritesJump.length; i++){
            if(i == (numFramesRun/2)) spriteCutHeight = height;
            scaledSpritesJump[i] = Bitmap.createBitmap(spriteSheetJump, (i * width), spriteCutHeight , width, height);
            spriteCutHeight = 0;
        }
        */

        animation.setFrames(scaledSpritesRun);
        animation.setDelay(90);
        startTime = System.nanoTime();
    }

    @Override
    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;
        if(elapsed > 100){
            score++;
            startTime = System.nanoTime();
        }
        if(!isJumping())
        {
            animation.update();
        }
        else{
            // player translation + jump animation

            preTime = currentTime;
            currentTime = MainThread.getTimeMillis();
            dt = currentTime - preTime;

            //System.out.print("Wert currentTime:" + currentTime + "  ");

            if (dt > 0.08) dt = 0.08; // 30fps --> 1/30; 60fps --> 1/60 //Speed of Jump
            //System.out.print("dt-Wert:"+dt + "  ");
            y = (int) (y+ 3*dt*dy);                         //*3, um Geschwindigkeit des Sprunges anzupassen
            //System.out.println("Y-Wert:"+y + "  ");
            dy = (int) (dy+ dt*playerGravity);
            //System.out.println("dy-Wert:"+dy + "  ");

            if(y > Constants.SCREEN_HEIGHT - height) //y > 1308
            {
                setJump(false);
                dt= 0;
                dy=standard_dy;

                y = Constants.SCREEN_HEIGHT - height - 1; //safety net (bottom)
            }
            if(y < 0)
            {
                dy = 0;
                y = 0; //safety roof
            }
            //System.out.println("Y-Wert nach IFs:"+y + "  ");
            hitBox.set((int)x,(int)y,(int)x+width,(int)y+height);
        }
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), (int)(x),(int)(y), null);
    }

    public int getScore() { return this.score; }
    public boolean isPlaying() { return playing; }

    public void setPlaying(boolean playing) { this.playing = playing; }

    public void resetDYA() { this.dYa = 0; }
    public void resteScore() { this.score = 0; }

    public boolean isJumping() { return this.jump; }
    public void setJump(boolean b) { jump = b; }

    public Rect getHitbox() {
        return hitBox;
    }
}