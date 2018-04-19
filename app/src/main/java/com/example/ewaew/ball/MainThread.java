package com.example.ewaew.ball;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Ewa Lyko on 13.04.2018.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    @Override
    public void run() {
        long starttime;
        long timeMillis = 1000/MAX_FPS;
        long waittime;
        int frameCount =0;
        long totalTime= 0;
        long targetTime = 1000/MAX_FPS;

        while (running)
        {
            starttime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){e.printStackTrace();}
            finally {
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){e.printStackTrace();}
                }
            }
            timeMillis= (System.nanoTime() - starttime)/1000000;
            waittime = targetTime - timeMillis;
            try {
                if(waittime>0)
                {
                    this.sleep(waittime);
                }
            }catch (Exception e){e.printStackTrace();}

            totalTime += System.nanoTime() - starttime;
            frameCount++;
            if (frameCount == MAX_FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }
}
