package com.example.dominic.gatav;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static int NAV_BAR_WIDTH;
    public static DisplayMetrics metrics;
    public static int WIDTH;
    public static int HEIGHT;

    //float initialX, initialY;

    private Bitmap bgSpriteSheet;
    private Background background;

    private Player supelMalio;
    private Rect playerHitbox;
    private Bitmap spritePlayerRun;

    private Bitmap coinSpriteSheet;
    private Rect coinHitBox;

    private MainThread thread;
    private ObstacleManager obstacleManager;

    private Intent gameOverIntent;

    public GamePanel(Context context){
        super(context);
        NAV_BAR_WIDTH = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
        metrics = context.getResources().getDisplayMetrics();
        Constants.SCREEN_WIDTH   =   metrics.widthPixels + NAV_BAR_WIDTH;
        Constants.SCREEN_HEIGHT  =   metrics.heightPixels;
        Constants.CURRENT_CONTEXT = context;

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        obstacleManager = new ObstacleManager(500);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(),this);

        //Option for loading resources without auto scale
        Constants.NO_SCALE = new BitmapFactory.Options();
        Constants.NO_SCALE.inScaled = false;

        //loading resources
        bgSpriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.bg_sprite_sheet, Constants.NO_SCALE);
        coinSpriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.yen_coin_sheet, Constants.NO_SCALE);
        spritePlayerRun = BitmapFactory.decodeResource(getResources(), R.drawable.player_run, Constants.NO_SCALE);

        //creating background and setting scrollspeed
        background = new Background(bgSpriteSheet, 5);

        supelMalio = new Player(spritePlayerRun, 77, 132, 8);

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
    public boolean onTouchEvent(MotionEvent event) {
        if(!supelMalio.isJumping())
        {
           //Game.PlayJumpSound();
        }
        supelMalio.setJump(true);
        return true;
    }

    public void update(){
        background.update();
        supelMalio.update();
        obstacleManager.update();

        if(obstacleManager.playerCollide(supelMalio))
        {
            gameOverIntent = new Intent(Constants.CURRENT_CONTEXT, GameOver.class);
            Constants.CURRENT_CONTEXT.startActivity(gameOverIntent);
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            //Gamelogic
            background.draw(canvas);
            supelMalio.draw(canvas);
            obstacleManager.draw(canvas);
        }
    }
}
