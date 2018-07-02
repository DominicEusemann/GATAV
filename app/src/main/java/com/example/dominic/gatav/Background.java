package com.example.dominic.gatav;

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
        if(x<-MainThread.canvas.getWidth()){
            x=0;
        }
    }

    public void draw(Canvas canvas){
        y=0;
        //testoutputs
        System.out.println("Canvas-Width: " + canvas.getWidth() + " Canvas-Height: " + canvas.getHeight());
        System.out.println("Image-Width: " + image.getWidth() + " Image-Height: " + image.getHeight());
        System.out.println("Scaled Width: " + image.getScaledWidth(canvas) + ", Scaled Height: " + image.getScaledHeight(canvas));

        canvas.drawBitmap(image, x, y, null);
        if(x<0){
            canvas.drawBitmap(image, x+canvas.getWidth(), y, null);

        }
    }

    public void setVector(int dx){
        this.dx = dx;
    }
}
