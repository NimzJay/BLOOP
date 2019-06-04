package com.example.hp.bloop;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {

    private Button startAgain, closeBtn, btnSave;
    private TextView score;
    private String totScore;
    EditText username;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        myDb = new DatabaseHelper(this);

        username = (EditText) findViewById(R.id.txtUser);
        btnSave = (Button) findViewById(R.id.btnSave);

        totScore = getIntent().getExtras().get("Score").toString();

        startAgain = (Button) findViewById(R.id.playAgainButton);
        closeBtn = (Button) findViewById(R.id.closeBtn);
        score = (TextView) findViewById(R.id.score2);

        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GameOverActivity.this, Main.class);
                startActivity(mainIntent);
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GameOverActivity.this, Main.class);
                startActivity(mainIntent);
            }
        });

        score.setText("SCORE: " + totScore);
        addData();
    }

    public void addData(){
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(username.getText().toString(),totScore.toString());

                        if(isInserted = true)
                            Toast.makeText(GameOverActivity.this, "Data Inserted.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(GameOverActivity.this, "Data Not Inserted.", Toast.LENGTH_LONG).show();

                        Cursor res =myDb.getAllData();
                        if(res.getCount()==0){

                            showMessage("Error" ,"No Data Found");
                            //show message
                            return ;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID:  " + res.getString(0)+ "\n");
                            buffer.append("NAME:  " + res.getString(1)+ "\n");
                            buffer.append("SCORE:  " + res.getString(2)+ "\n\n");

                        }
                        // show all the data
                        showMessage("DATA",buffer.toString());
                   }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

}
