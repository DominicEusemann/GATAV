package com.example.dominic.gatav;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private Bitmap scaledImage;

    private int x, y, dx;

    public Background(Bitmap res){
        image = res;
        scaledImage = Bitmap.createScaledBitmap(image, GamePanel.screenWidth, GamePanel.screenHeight, false);


    }

    public Bitmap getImage() {
        return this.image;
    }


    public void update(){
        x+=dx;
        if(x<-scaledImage.getWidth()){
            x=0;
        }
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(scaledImage, x, y, null);
        canvas.drawBitmap(scaledImage, x+scaledImage.getWidth(), y, null);

        if(x<0){
            canvas.drawBitmap(scaledImage, x+ 2*scaledImage.getWidth(), y, null);
        }
    }

    public void setVector(int dx){
        this.dx = dx;
    }

}
