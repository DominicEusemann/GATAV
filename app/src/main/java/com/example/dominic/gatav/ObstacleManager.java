package com.example.dominic.gatav;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObstacleManager {
    //higherindex
    private ArrayList<Obstacle> obstacles;
    private int obstacleGap;
    private double startTime;
    private double globalSpawnTime;
    private double globalStartTime;
    int counter;
    private double globalStopTime;
    private int score;



    public int getScore() { return score; }
    public void setScore(int i){ score = i; }

    public ObstacleManager(int obstacleGap){
        globalStopTime = 4000.0;
        startTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        this.obstacleGap = obstacleGap;
        globalSpawnTime = System.currentTimeMillis();
        globalStartTime = System.currentTimeMillis();
        counter = 0;
        //populateObstacles();
    }

    private void populateObstacles()
    {
    }

    public int playerCollide(Player player)
    {
        for (Obstacle ob : obstacles)
        {
            if(ob.playerCollide(player))
            {
                ob.setHitted(true);
                return ob.getID(); //Hit
            }
        }
        return -1; //No Hit
    }

    public void update()
    {
        double elapsedTime = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();



        float speed = Constants.SCREEN_WIDTH / 10000.0f;
        float velocity = speed * (float)elapsedTime;

        if(velocity > 50000.0f) velocity = 50000.0f;

        globalSpawnTime = System.currentTimeMillis();

        //Spawn "random"
        if(globalSpawnTime > globalStartTime+(counter*globalStopTime))
        {
            counter++;
            if(Math.random()> Math.random())
            {
                obstacles.add(new Obstacle(1, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT - 64, 0, 0));
            }
            else {
                obstacles.add(new Obstacle(0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT - 64, 0, 0));
            }
        }

        for(Obstacle ob : obstacles)
        {
            ob.decrementX(velocity);
            ob.update();
        }
    }

    public void draw(Canvas canvas)
    {
        for (Obstacle ob : obstacles)
        {
            ob.draw(canvas);
            if(ob.getHitted())
                ob.destroyObstacle();
        }
    }
}
