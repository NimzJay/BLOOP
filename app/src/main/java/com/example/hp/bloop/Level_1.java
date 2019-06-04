package com.example.hp.bloop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;

public class Level_1 extends View
{
    private Bitmap box[] = new Bitmap[2];
    private int boxX = 10;
    private int boxY;
    private int boxSpeed;

    private int canvasWidth, canvasHeight;

    private Bitmap green;
    private int greenX, greenY, greenSpeed = 20;

    private Bitmap red;
    private int redX, redY, redSpeed = 10;

    private Bitmap black;
    private int blackX, blackY, blackSpeed = 10;

    private int score, lifeCount;

    private boolean touch = false;

    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    //private Background bg;

    public Level_1(Context context)
    {
        super(context);
        box[0] = BitmapFactory.decodeResource(getResources(), R.drawable.box); //

        box[1] = BitmapFactory.decodeResource(getResources(), R.drawable.box2);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.mmm2);

        //bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.fore0));

        red = BitmapFactory.decodeResource(getResources(), R.drawable.red);
        green = BitmapFactory.decodeResource(getResources(), R.drawable.rainbow);
        black = BitmapFactory.decodeResource(getResources(), R.drawable.black);


        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.life);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.delife);

        boxY = 550;
        score = 0;
        lifeCount = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0, 0, null);

        //canvas.drawBitmap(box[1], 0, 0, null);
        int minBoxY = box[0].getHeight();
        int maxBoxY = canvasHeight - box[0].getHeight() * 3;
        boxY = boxY + boxSpeed;
        if (boxY < minBoxY) {
            boxY = minBoxY;
        }
        if (boxY > maxBoxY) {
            boxY = maxBoxY;
        }
        boxSpeed = boxSpeed + 2;

        if (touch) {
            canvas.drawBitmap(box[1], boxX, boxY, null);
            touch = false;
        }
        else {
            canvas.drawBitmap(box[0], boxX, boxY, null);
        }


        greenX = greenX - greenSpeed;
        if (hitBallChecker(greenX, greenY)) {
            score = score + 10;
            greenX = - 100;
        }
        if (greenX < 0) {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxBoxY - minBoxY)) + minBoxY;
        }
        canvas.drawBitmap(green, greenX, greenY, null);


        redX = redX - redSpeed;
        if (hitBallChecker(redX, redY)) {
            score = score + 20;
            redX = - 100;
        }
        if (redX < 0) {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxBoxY - minBoxY)) + minBoxY;
        }
        canvas.drawBitmap(red, redX, redY, null);


        blackX = blackX - blackSpeed;
        if (hitBallChecker(blackX, blackY)) {
            blackX = - 100;
            lifeCount--;
            if (lifeCount == 0){
                Toast.makeText(getContext(), "Game Over!", Toast.LENGTH_SHORT).show();

                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("Score", score);
                getContext().startActivity(gameOverIntent);
            }
        }
        if (blackX < 0) {
            blackX = canvasWidth + 21;
            blackY = (int) Math.floor(Math.random() * (maxBoxY - minBoxY)) + minBoxY;
        }
        canvas.drawBitmap(black, blackX, blackY, null);

        canvas.drawText("Score: " + score, 20, 60, scorePaint);

        for (int i=0; i<3; i++){
            int x = (int) (400 + life[0].getWidth() * 1.5 * i);
            int y = 30;
            if (i < lifeCount){
                canvas.drawBitmap(life[0], x, y, null);
            }
            else{
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
        
        if(score > 100)
        {
            /*canvas.drawColor(R.drawable.wall5);
            Intent lvl2 = new Intent(getContext(), LevelUp2.class);
            //Intent lv2 = new Intent(Level_1.this, Level_2.class);
            lvl2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //gameOverIntent.putExtra("Score", score);
            getContext().startActivity(lvl2);*/

            Intent nextLevel2 = new Intent(getContext(), level1Up.class);
            nextLevel2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //nextLevel2.putExtra("Score", score);
            getContext().startActivity(nextLevel2);

        }
        //canvas.drawBitmap(life[0], 400, 10, null);
        //canvas.drawBitmap(life[0], 500, 10, null);
        //canvas.drawBitmap(life[0], 600, 10, null);

    }

    public boolean hitBallChecker(int x, int y)
    {
        if (boxX < x && x < (boxX + box[0].getWidth()) && boxY < y && y < (boxY + box[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            boxSpeed = -30;
        }
        return true;
    }
}
