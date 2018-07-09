package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class ObstacleManager {
    //higherindex
    private ArrayList<Obstacle> obstacles;
    private int obstacleGap;
    private long startTime;
    private Bitmap coinSpriteSheet;

    public ObstacleManager(int obstacleGap){

        startTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        this.obstacleGap = obstacleGap;
        populateObstacles();
    }

    private void populateObstacles(){

        int currX = (3*Constants.SCREEN_WIDTH/4);
        int spawnnumber = 0;
        //while(currX > 0){
            spawnnumber = (int)(Math.random()*10);
            coinSpriteSheet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yen_coin_sheet, Constants.NO_SCALE);

            obstacles.add(new Obstacle(0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT - 64, 0, 0));
            /*switch(spawnnumber) {
                case (0):
                    //Spawn YenCoin
                    break;
                default:
                    //Spawn Fire
                    break;
            }*/
        //}
    }

    public boolean playerCollide(Player player)
    {
        for (Obstacle ob : obstacles)
        {
            if(ob.playerCollide(player)) return true;
        }
        return false;
    }

    public void update(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_WIDTH / 10000.0f;
        float velocity = speed * elapsedTime;
        if(velocity > 50000.0f) velocity = 50000.0f;
        for(Obstacle ob : obstacles){
            ob.decrementX(velocity);
            ob.update();
        }
    }

    public void draw(Canvas canvas)
    {
        for (Obstacle ob : obstacles)
        {
            ob.draw(canvas);
        }
    }
}
