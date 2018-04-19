package com.example.ewaew.ball.Scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.ewaew.ball.Constants;
import com.example.ewaew.ball.Obstacle.ObstacleManager;
import com.example.ewaew.ball.R;
import com.example.ewaew.ball.RectPlayer;
import com.example.ewaew.ball.Interfaces.Scene;
import com.example.ewaew.ball.Sensors.BackgroundColor;
import com.example.ewaew.ball.Sensors.LightData;
import com.example.ewaew.ball.Sensors.OrientationData;

/**
 * Created by Ewa Lyko on 13.04.2018.
 */

public class GameplayScene implements Scene {




    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private boolean movingPlayer = false;
    private boolean gameOver = false;

    private long gameOverTime;

    private OrientationData orientationData;
    private long frameTime;

    private LightData lightData;
    private BackgroundColor backgroundColor;

    public GameplayScene() {
        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(255,0,0));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(Constants.PLAYER_GAP,Constants.OBSTACLE_GAP,Constants.OBSTACLE_HEIGHT, Color.BLACK);

        orientationData = new OrientationData();
        orientationData.register();
        frameTime = System.currentTimeMillis();

        lightData = new LightData();
        lightData.register();
    }


    @Override
    public void update() {
        if(!gameOver)
        {
            if(frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;
            int elapsedTime = (int)(System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null)
            {
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                float xSpeed = 2 * roll * Constants.SCREEN_WIDTH / 1000f;
                float ySpeed =  pitch * Constants.SCREEN_HEIGHT / 1000f;

                playerPoint.x += Math.abs(xSpeed*elapsedTime) >5 ? xSpeed*elapsedTime : 0;
                playerPoint.y -= Math.abs(ySpeed*elapsedTime) >5 ? ySpeed*elapsedTime : 0;

            }
            if(playerPoint.x < 0 )
                playerPoint.x = 0;
            else if(playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;

            if(playerPoint.y < 0 )
                playerPoint.y = 0;
            else if(playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;
            if(lightData.getLightData()!=0)
            {
                backgroundColor = new BackgroundColor(lightData.getLightData());
            }

            player.update(playerPoint);
            obstacleManager.update();
            if(obstacleManager.playerCollide(player))
            {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
            obstacleManager.playerScore(player);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(backgroundColor.getColor()[0],backgroundColor.getColor()[1],backgroundColor.getColor()[2]));
        player.draw(canvas);
        obstacleManager.draw(canvas);

        if (gameOver)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.game_over);
            canvas.drawBitmap(bitmap,(Constants.SCREEN_WIDTH-bitmap.getWidth())/2, (Constants.SCREEN_HEIGHT-bitmap.getHeight())/2,null);

        }
    }


    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && player.getRectangle().contains((int)event.getX(),(int)event.getY()))
                    movingPlayer = true;
                if (gameOver && System.currentTimeMillis() - gameOverTime > 500)
                {
                    reset();
                    gameOver = false;
                    orientationData.newGame();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && movingPlayer)
                    playerPoint.set((int)event.getX(),(int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }

    }
    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(Constants.PLAYER_GAP,Constants.OBSTACLE_GAP, Constants.OBSTACLE_HEIGHT, Color.BLACK);
        movingPlayer = false;


    }
}
