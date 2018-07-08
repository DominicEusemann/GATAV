package com.example.dominic.gatav;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;

public class ObstacleManager {
    //higherindex
    private ArrayList<Obstacle> obstacles;
    private int obstacleGap;
    private long startTime;

    public ObstacleManager(int obstacleGap){

        startTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        this.obstacleGap = obstacleGap;
        populateObstacles();
    }

    private void populateObstacles(){

        int currX = 4*GamePanel.WIDTH/3;
        int spawnnumber = 0;
        while(obstacles.get(obstacles.size() - 1).getHitbox().left > GamePanel.WIDTH){
            spawnnumber = (int)Math.random()*10;

            switch(spawnnumber) {
                case (0):
                    //Spawn YenCoin

                    break;

            }
        }
    }

    public void update(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_WIDTH / 10000.0f;
        float velocity = speed * elapsedTime;
        if(velocity > 50000.0f) velocity = 50000.0f;
        for(Obstacle ob : obstacles){
            ob.incrementX(velocity);
        }
    }
}
