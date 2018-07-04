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

    private Bitmap bgBitmap;
    private Background background;

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

        BitmapFactory.Options noScale = new BitmapFactory.Options();
        noScale.inScaled = false;
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg, noScale);

        Player player = new Player();

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
        final float scaleFactorX = (canvas.getWidth() / WIDTH) * 1.0f;
        final float scaleFactorY = (canvas.getHeight() / HEIGHT) * 1.0f;
        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);

            System.out.println("Canvas-Width: " + canvas.getWidth() + " Canvas-Height: " + canvas.getHeight());
            System.out.println("Image-Width: " + bgBitmap.getWidth() + " Image-Height: " + bgBitmap.getHeight());
            System.out.println("Scaled Width: " + bgBitmap.getScaledWidth(canvas) + ", Scaled Height: " + bgBitmap.getScaledHeight(canvas));
            canvas.restoreToCount(savedState);
        }
    }
}
