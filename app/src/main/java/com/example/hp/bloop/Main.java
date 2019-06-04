package com.example.hp.bloop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends AppCompatActivity
{
    private Level_1 gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;
    DatabaseHelper MyDb;
    EditText editText,editText2;
    Button button, settings, manual;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play2);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Main.this, Home.class);
                startActivity(mainIntent);
            }
        });

        /*settings = (Button) findViewById(R.id.btnSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, SettingsActivity.class);
                startActivity(intent);

            }
        });

        manual = (Button) findViewById(R.id.btnManual);
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manualIntent = new Intent(Main.this, userManual.class);
                startActivity(manualIntent);
            }
        });*/

        /*gameView = new Level_1(this);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);*/
    }

}
