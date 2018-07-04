package com.example.dominic.gatav;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;

    public static int statusbarHeight;

    private Bitmap backgroundSprite;
    private Background background;

    private Bitmap coinSpriteSheet;
    private YenCoin coin;

    private MainThread thread;

    public GamePanel(Context context){
        super(context);

        Resources resources = context.getResources();
        int resourceIdStatusbar = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceIdStatusbar > 0) {
            statusbarHeight = resources.getDimensionPixelSize(resourceIdStatusbar);
        }

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
        coin = new YenCoin(coinSpriteSheet, GamePanel.WIDTH,
                                            GamePanel.HEIGHT,
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
        //scalefactors for background
        final float scaleFactorX = (canvas.getWidth() / WIDTH) * 1.0f;
        final float scaleFactorY = (canvas.getHeight() / HEIGHT) * 1.0f;
        if(canvas != null){
            //cause draw gets called multiple times
            //we need to save the canvas state and restore it after drawing
            //so the resource does not scale to infinity
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);

            //draw resources
            background.draw(canvas);
            coin.draw(canvas);

            /* prints to debug scaling
            System.out.println("Canvas-Width: " + canvas.getWidth() + " Canvas-Height: " + canvas.getHeight());
            System.out.println("Image-Width: " + bgBitmap.getWidth() + " Image-Height: " + bgBitmap.getHeight());
            System.out.println("Scaled Width: " + bgBitmap.getScaledWidth(canvas) + ", Scaled Height: " + bgBitmap.getScaledHeight(canvas));
            */

            //restoring saved canvas state before scaling
            canvas.restoreToCount(savedState);
        }
    }
}
