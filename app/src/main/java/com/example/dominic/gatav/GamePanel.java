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

    //float initialX, initialY;

    private Bitmap bgSpriteSheet;
    private Background background;

    private Player supelMalio;
    private Bitmap spritePlayerRun;

    private Bitmap coinSpriteSheet;
    private YenCoin coin;

    private MainThread thread;

    public GamePanel(Context context){
        super(context);
        NAV_BAR_WIDTH = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
        metrics = context.getResources().getDisplayMetrics();
        WIDTH   =   metrics.widthPixels + NAV_BAR_WIDTH;
        HEIGHT  =   metrics.heightPixels;

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
        spritePlayerRun = BitmapFactory.decodeResource(getResources(), R.drawable.player_run, noScale);

        //creating background and setting scrollspeed
        background = new Background(bgSpriteSheet, 5);

        supelMalio = new Player(spritePlayerRun, 77, 132, 8);

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

   /* @Override
    public boolean onTouchEvent(MotionEvent event){

        int touchMe = event.getActionMasked();

        switch(touchMe)
        {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();
                //Action was DOWN
                break;

            case MotionEvent.ACTION_MOVE:
                //Action was MOVE
                break;

            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                float finalY = event.getY();


                if (initialX < finalX) {
                    //Left to Right swipe performed
                }

                if (initialX > finalX) {
                    //Right to Left swipe performed
                }

                if (initialY < finalY) {
                    //Up to Down swipe performed
                }

                if (initialY > finalY) {
                    //Down to Up swipe performed
                    supelMalio.setJump(true);
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                //Action was CANCEL
                break;

            case MotionEvent.ACTION_OUTSIDE:
                //Movement occurred outside bounds of current screen element
                break;
        }

        Player.setJump(true);
        return super.onTouchEvent(event);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        supelMalio.setJump(true);
        return true;
    }

    public void update(){
        background.update();
        coin.update();
        supelMalio.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            //Gamelogic
            background.draw(canvas);
            coin.draw(canvas);
            supelMalio.draw(canvas);
        }
    }
}
