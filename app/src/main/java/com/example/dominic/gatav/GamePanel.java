package com.example.dominic.gatav;

import android.content.Context;
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
    private Bitmap bgBitmap;
    private Background background;

    private MainThread thread;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(),this);

        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        //bgBitmap = Bitmap.createScaledBitmap(bgBitmap, 1920, 1080, false);

        background = new Background(bgBitmap);
        background.setVector(-5);

        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
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
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        //final float scaleFactorX = (canvas.getWidth() / WIDTH) * 1.0f;
        //final float scaleFactorY = (canvas.getHeight() / HEIGHT) * 1.0f;
        if(canvas != null){
            final int savedState = canvas.save();
            //canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);
            //canvas.restoreToCount(savedState);
        }
    }
}
