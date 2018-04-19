package com.example.ewaew.ball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button easyButton;
    private Button hardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        Constants.SCREEN_HEIGHT = displayMetrics.heightPixels;
        Constants.SCREEN_WIDTH = displayMetrics.widthPixels;

        setContentView(R.layout.activity_main);

        initialize();

        onClick();
    }

    private void onClick() {

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main2Activity.start(getApplicationContext(),(int)(0.4*Constants.SCREEN_WIDTH),(int)(0.5*Constants.SCREEN_WIDTH),(int)(0.04*Constants.SCREEN_WIDTH));
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main2Activity.start(getApplicationContext(),(int)(0.25*Constants.SCREEN_WIDTH),(int)(0.5*Constants.SCREEN_WIDTH),(int)(0.03*Constants.SCREEN_WIDTH));

            }
        });
    }

    private void initialize() {
        easyButton = findViewById(R.id.easy_button);
        hardButton = findViewById(R.id.hard_button);
    }
}
