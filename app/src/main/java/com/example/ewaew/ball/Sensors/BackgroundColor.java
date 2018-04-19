package com.example.ewaew.ball.Sensors;

/**
 * Created by Ewa Lyko on 19.04.2018.
 */

public class BackgroundColor {

    private static final float LIGHT_TENT = 100;
    private int [] color;

    public BackgroundColor(float lightTent)
    {
        color = new int[3];
        if(lightTent<LIGHT_TENT)
            setDarkColor();
        else
            setBrightColor();
    }

    private void setBrightColor() {
        color[0] = 240;
        color[1] = 154;
        color[2] = 238;
    }

    private void setDarkColor() {
        color[0] = 100;
        color[1] = 100;
        color[2] = 100;
    }
    public int[] getColor()
    {
        return color;
    }
}
