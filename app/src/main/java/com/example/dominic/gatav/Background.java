package com.example.dominic.gatav;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res){
        image = res;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void update(){
        x+=dx;
        if(x<-image.getWidth()){
            x=0;
        }
    }

    public void draw(Canvas canvas){
        y = GamePanel.statusbarHeight;
        //testoutputs

        canvas.drawBitmap(image, x, y, null);
        if(x<0){
            canvas.drawBitmap(image, x+image.getWidth(), y, null); }
    }

    public void setVector(int dx){
        this.dx = dx;
    }

}
