package com.example.ewaew.ball.Obstacle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.ewaew.ball.Constants;
import com.example.ewaew.ball.Interfaces.GameObject;
import com.example.ewaew.ball.RectPlayer;

/**
 * Created by Ewa Lyko on 13.04.2018.
 */

public class Obstacle implements GameObject {

    private Rect rectangle;
    private Rect rectangle2;
    private Rect scoreR;
    private int color;
    private boolean scored;

    public Obstacle(int rectHeight, int color,int startX, int startY, int playerGap)
    {
        this.color = color;
        rectangle = new Rect(0,startY,startX,startY+rectHeight);
        rectangle2 = new Rect(startX+playerGap,startY, Constants.SCREEN_WIDTH,startY+rectHeight);
        scoreR = new Rect(startX,startY,startX+playerGap,startY+rectHeight);
        scored = false;
    }

    public Rect getRectangle()
    {
        return rectangle;
    }

    public boolean playerCollide(RectPlayer player)
    {
        return Rect.intersects(rectangle,player.getRectangle()) || Rect.intersects(rectangle2,player.getRectangle());
    }
    public boolean playerScore(RectPlayer player)
    {
        if(Rect.intersects(scoreR,player.getRectangle()))
        {
            scored = true;
            return true;
        }
        return false;
    }

    public void incrementY(float y )
    {
        rectangle.top+=y;
        rectangle.bottom+=y;
        rectangle2.top+=y;
        rectangle2.bottom+=y;
        scoreR.top+=y;
        scoreR.bottom+=y;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        Paint paint1 = new Paint();
        paint1.setColor(Color.MAGENTA);
        canvas.drawRect(rectangle,paint);
        canvas.drawRect(rectangle2,paint);
    }

    @Override
    public void update() {
    }

    public boolean getScored() {
        return scored;
    }
}
