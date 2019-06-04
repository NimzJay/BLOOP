package com.example.hp.bloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class level3Up extends AppCompatActivity {

    private Button startAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_up);

        startAgain = (Button) findViewById(R.id.btnCon);

        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(level3Up.this, LevelUp4.class);
                startActivity(mainIntent);
            }
        });

        /*closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GameOverActivity.this, Main.class);
                startActivity(mainIntent);
            }
        });*/
    }
}
