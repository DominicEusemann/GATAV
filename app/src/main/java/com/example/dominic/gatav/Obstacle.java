package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Obstacle implements GameObject{

    private int id;
    private int imagecount;
    Bitmap[] images;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private boolean hitted;
    private Rect hitBox;

    public int getID(){ return id;}
    public void setID(int id) { this.id = id;}

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }

    public double getX() { return x; }
    public double getY() { return y; }

    public double getDx() { return dx; }
    public double getDy() { return dy; }

    public boolean getHitted(){ return hitted;}
    public void setHitted(boolean b) { this.hitted = b; }

    public void setHitbox(Rect hitBox){ this.hitBox = hitBox; }
    public Rect getHitbox(){ return hitBox; }

    public void decrementX(double dX){
        x -= dX;
        hitBox.left -= dX;
        hitBox.right -= dX;
    }



    private Bitmap coin_spriteSheet;
    private Bitmap flame_spriteSheet;
    private Animation animation = new Animation();
    private long startTime;

    private int width;
    private int height;

    public Obstacle(int id, int posX, int posY, int dX, int dY){
        this.coin_spriteSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yen_coin_sheet, Constants.NO_SCALE);
        this.flame_spriteSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.endx, Constants.NO_SCALE);

        this.hitted = false;
        this.id = id;
        setX(posX);
        setY(posY);
        setDx(dX);
        setDy(dY);





        switch (id){
            case(0):
                this.width = 32;
                this.height = 32;
                this.imagecount = 5;

                images = new Bitmap[imagecount];
                for(int i=0; i<images.length; i++) {
                    images[i] = Bitmap.createBitmap(coin_spriteSheet, (i * width), 0, width, height);
                }
                animation.setFrames(images);
                animation.setDelay(120);
                startTime = System.nanoTime();
                break;
            case(1):
                this.width = 109;
                this.height = 109;
                this.imagecount = 1; //<--- change back again

                images = new Bitmap[imagecount];
                for(int i=0; i<images.length; i++) {
                    images[i] = Bitmap.createBitmap(flame_spriteSheet, (i * width), 0, width, height);
                }
                animation.setFrames(images);
                animation.setDelay(120);
                startTime = System.nanoTime();
                break;

        }
        Rect hitBox = new Rect(posX,posY,posX+width,posY+height);
        setHitbox(hitBox);
    }

    public int getHeight() { return height; }

    public void update(){ animation.update(); }

    public void draw(Canvas canvas){ canvas.drawBitmap(animation.getImage(), (int)this.x, (int)this.y, null); }


    public void destroyObstacle()
    {
        for(int i=0; i<imagecount; i++)
        {
            //images[i].recycle();
            hitBox.set((int)x,(int) y,(int)x,(int) y);
        }
    }

    public boolean playerCollide(Player player){
        return Rect.intersects(hitBox, player.getHitbox());
    }


}
