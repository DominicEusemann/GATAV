package com.example.dominic.gatav;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;

    public static DisplayMetrics metrics;
    public static int screenWidth;
    public static int screenHeight;

    private Bitmap backgroundSprite;
    private Background background;

    private Bitmap coinSpriteSheet;
    private YenCoin coin;

    private MainThread thread;

    public GamePanel(Context context){
        super(context);

        metrics = context.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(),this);

        //Option for loading resources without auto scale
        BitmapFactory.Options noScale = new BitmapFactory.Options();
        noScale.inScaled = false;

        //loading resources
        backgroundSprite = BitmapFactory.decodeResource(getResources(), R.drawable.bg, noScale);
        coinSpriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.yen_coin_sheet, noScale);

        //creating background and setting scrollspeed
        background = new Background(backgroundSprite);
        background.setVector(-5);

        //creating a coin
        coin = new YenCoin(coinSpriteSheet, 0,
                                            0,
                                            0, 0, 32, 32,5);

        //set and start thread
        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //terminate thread
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) { e.printStackTrace(); }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void update(){
        background.update();
        coin.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            //draw Game
            background.draw(canvas);
            coin.draw(canvas);
        }
    }
}
