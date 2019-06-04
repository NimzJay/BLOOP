package com.example.hp.bloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BloopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloop);

        Thread thread = new Thread()
        {
            @Override
            public  void run(){
                try {
                    sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    /*Intent mainIntent = new Intent(BloopActivity.this, Main.class);
                    startActivity(mainIntent);*/
                    Intent mainIntent = new Intent(BloopActivity.this, Main.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
