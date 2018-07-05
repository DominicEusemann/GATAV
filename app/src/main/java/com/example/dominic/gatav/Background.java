package com.example.dominic.gatav;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    public static final int spriteHeight = 160;

    private Bitmap spriteSheet;
    private Bitmap staticLayer;

    private Bitmap[] scaledLayers;

    private int[] x;
    private int y;

    private static final int[] scrollSpeed = new int[]{-1, -2, -3, -5};


    public Background(Bitmap spriteSheet, int layerCount){
        this.spriteSheet = spriteSheet;
        x = new int[]{0,0,0,0};
        y = spriteHeight;

        scaledLayers = new Bitmap[layerCount-1];
        //first 2 sprites(static background, first layer) extracted from spritesheet, scaled to displaysize
        //they are seperated cause the sprites have different widths ( first two have the same length )


        staticLayer = Bitmap.createBitmap(spriteSheet, 0, 0, (spriteSheet.getWidth()/2), spriteHeight);
        staticLayer = Bitmap.createScaledBitmap(staticLayer, GamePanel.WIDTH, GamePanel.HEIGHT, false);

        scaledLayers[0] = Bitmap.createBitmap(spriteSheet, (spriteSheet.getWidth() / 2), 0, (spriteSheet.getWidth() / 2), spriteHeight);
        scaledLayers[0] = Bitmap.createScaledBitmap(scaledLayers[0], GamePanel.WIDTH, GamePanel.HEIGHT, false);

        //extract third to last sprite(scrolling layers) from spritesheet and scale to displaysize
        //scaledLayer[0] contains the first scrolling layer(second sprite from sheet) and so on
        //scaling factors for scrolling layers should be width bound which means y-coordinate get scaled with
        //scalefactorX too, to keep aspect ratio

        for(int i=1; i<scaledLayers.length; i++) {
            scaledLayers[i] = Bitmap.createBitmap(spriteSheet, 0, i * y, spriteSheet.getWidth(), spriteHeight);
            scaledLayers[i] = Bitmap.createScaledBitmap(scaledLayers[i], GamePanel.WIDTH, spriteHeight * (GamePanel.WIDTH / spriteSheet.getWidth()), false);
        }

    }

    public Bitmap getImage() {
        return this.staticLayer;
    }


    public void update(){
        //update x coordinate for scrolling layers by adding a scrollvector x, reset when leaving screen

        for (int i = 0; i < x.length; i++) {
            x[i] += scrollSpeed[i];
            if (x[i] < (-scaledLayers[i].getWidth())) {
                x[i] = 0;
            }
        }

    }

    public void draw(Canvas canvas){

        //draw static background at x,y = 0
        canvas.drawBitmap(staticLayer, 0, 0, null);

        //loop to draw scrolling layers
        for(int i=0; i<scaledLayers.length; i++){
            y = 0;
            if(i > 0) y =  scaledLayers[i].getHeight() + GamePanel.TITLE_BAR_HEIGHT;
            canvas.drawBitmap(scaledLayers[i], x[i], y, null);
            canvas.drawBitmap(scaledLayers[i], (x[i] + scaledLayers[i].getWidth()), y, null);

            if(x[i]<0){
                canvas.drawBitmap(scaledLayers[i], (x[i] + (2 * scaledLayers[i].getWidth())), y, null);
            }
        }


    }

}
