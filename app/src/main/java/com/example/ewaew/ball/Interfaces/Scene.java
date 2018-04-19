package com.example.ewaew.ball.Interfaces;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Ewa Lyko on 13.04.2018.
 */

public interface Scene {
    void update();
    void draw(Canvas canvas);
    void receiveTouch(MotionEvent event);
}
