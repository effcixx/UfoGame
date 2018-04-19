package com.example.ewaew.ball.Obstacle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.ewaew.ball.Constants;
import com.example.ewaew.ball.RectPlayer;

import java.util.ArrayList;

/**
 * Created by Ewa Lyko on 13.04.2018.
 */

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;

    private long startTime;
    private long initTime;

    private int score;

    public ObstacleManager(int playerGap,int obstacleGap, int obstacleHeight,int color){
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;
        score = 0;
        startTime= initTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        populateObstacles();

    }

    public boolean playerCollide(RectPlayer player)
    {
        for(Obstacle ob: obstacles)
        {
            if(ob.playerCollide(player))
                return true;
        }
        return false;
    }
    public boolean playerScore(RectPlayer player)
    {
        for(Obstacle ob: obstacles)
        {
            if(!ob.getScored() && ob.playerScore(player))
            {
                score++;
                return true;
            }

        }
        return false;
    }
    private void populateObstacles() {

        int currY = -5* Constants.SCREEN_HEIGHT/4;
        while (currY<0)
        {
            int xStart =(int) (Math.random()*(Constants.SCREEN_WIDTH-playerGap));
            obstacles.add(new Obstacle(obstacleHeight,color,xStart,currY,playerGap));
            currY += obstacleHeight+obstacleGap;
        }
    }
    public void update()
    {
        if(startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;

        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1+(startTime - initTime)/2000))*Constants.SCREEN_HEIGHT/(10000.0f);
        for(Obstacle ob : obstacles)
            ob.incrementY(speed*elapsedTime);
        if (obstacles.get(obstacles.size()-1).getRectangle().top >= Constants.SCREEN_HEIGHT)
        {
            int xStart =(int) (Math.random()*(Constants.SCREEN_WIDTH-playerGap));
            obstacles.add(0,new Obstacle(obstacleHeight,color,xStart,obstacles.get(0).getRectangle().top - obstacleHeight-obstacleGap , playerGap));
            obstacles.remove(obstacles.size()-1);
        }

    }
    public void draw(Canvas canvas)
    {
        for (Obstacle ob: obstacles)
            ob.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);
        canvas.drawText(""+score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }
}
