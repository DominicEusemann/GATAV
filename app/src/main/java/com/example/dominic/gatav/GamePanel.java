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
    public static int NAV_BAR_WIDTH;
    public static int TITLE_BAR_HEIGHT;
    public static DisplayMetrics metrics;
    public static int WIDTH;
    public static int HEIGHT;

    private Bitmap bgSpriteSheet;
    private Background background;

    private Bitmap coinSpriteSheet;
    private YenCoin coin;

    private MainThread thread;

    public GamePanel(Context context){
        super(context);
        NAV_BAR_WIDTH = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
        TITLE_BAR_HEIGHT = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("title_bar_height", "dimen", "android"));
        metrics = context.getResources().getDisplayMetrics();
        WIDTH =   metrics.widthPixels + NAV_BAR_WIDTH;
        HEIGHT = metrics.heightPixels;

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
        bgSpriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.bg_sprite_sheet, noScale);
        coinSpriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.yen_coin_sheet, noScale);

        //creating background and setting scrollspeed
        background = new Background(bgSpriteSheet, 5);

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
