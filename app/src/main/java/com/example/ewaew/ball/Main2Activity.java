package com.example.ewaew.ball;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Main2Activity extends AppCompatActivity {


    private final static String PLAYER_GAP = "PLAYER_GAP";
    private final static String OBSTACLE_GAP = "OBSTACLE_GAP";
    private final static String  OBSTACLE_HEIGHT="OBSTACLE_HEIGHT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        Intent intent = this.getIntent();
        int playerGap = intent.getIntExtra(PLAYER_GAP,0);
        int obstacleGap =intent.getIntExtra(OBSTACLE_GAP,0);
        int obstacleHeight = intent.getIntExtra(OBSTACLE_HEIGHT,0);

        Constants.OBSTACLE_GAP = obstacleGap;
        Constants.OBSTACLE_HEIGHT = obstacleHeight;
        Constants.PLAYER_GAP = playerGap;



        setContentView(new GamePanel(this));

    }
    public static void start(Context context,int playerGap, int obstacleGap, int obstacleHeight) {
        Intent starter = new Intent(context, Main2Activity.class);
        starter.putExtra(PLAYER_GAP,playerGap);
        starter.putExtra(OBSTACLE_GAP,obstacleGap);
        starter.putExtra(OBSTACLE_HEIGHT,obstacleHeight);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }
}
