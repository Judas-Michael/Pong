package com.judas.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;

/**
 * Created by judas on 5/10/2018.
 */

public class GameState {

    final int width = 350;
    final int height = 500; //play surface width and height!

    //final variables can't be changed, but they can be used and passed

    final int paddleLength = 55; //this could also be set to change as levels go on? Maybe impliment timed levels?
    final int paddleThick = 10;
    int leftPaddleAcross = 25; //the amount of which the paddle starts above the bottom of the screen -- for aesethetics
    final int leftPaddleUp =  (height/2) - (paddleLength / 2); //the amount of which the paddle can move horizontal
    int rightPaddleAcross = 325; //this amount is the opposite of the other paddle because it's on the other side!
    final int rightPaddleUp = (height/2) - (paddleLength / 2); //same amount as before
    final int paddleSpeed = 5; //how fast you can move the paddle!

    final int ball = 15;//this is how many pixels the ball will be
    int ballAcross = 100;
    int ballUp = 100;
    int ballAcrossSpeed = 5; //how fast the ball moves. We could change these variables and set them by asking users the difficulty they want to play on?
    int ballDownSpeed = 5;


    public GameState() //empty constructor
    {
    }

    public void update() {

        ballAcross += ballAcrossSpeed; //this is saying that the balls current point on the X access adds pixels varied by speed -- we're making the balls MOVEEEEEEEEEEEEEE
        ballUp += ballDownSpeed;

        if(ballAcross > height || ballAcross < 0) //this reverses speed if the ball hits the ceiling
            ballAcrossSpeed *= -1;

        if(ballAcross > leftPaddleAcross && ballAcross < leftPaddleAcross+paddleLength && ballUp < leftPaddleUp)
            ballDownSpeed *= -1; //reverses speed if paddle is hit DIRECTLY

        if(ballAcross > rightPaddleAcross && ballAcross < rightPaddleAcross+paddleLength
                && ballUp > rightPaddleUp)
            ballDownSpeed *= -1; //reverse speed if paddle is hit DIRECTLY

        if(ballAcross > rightPaddleAcross || ballAcross < leftPaddleAcross)
            ballAcross = 100; ballUp =100; // resets the board if the ball goes past the paddles without hitting them!


    }
    public boolean onKeyDown(int buttonID, KeyEvent popUp)
    {
        if(buttonID == KeyEvent.KEYCODE_DPAD_UP) //makes paddle go up!
        {
            leftPaddleAcross += paddleSpeed;
            rightPaddleAcross -= paddleSpeed;
        }

        if(buttonID == KeyEvent.KEYCODE_DPAD_DOWN) //makes paddle go down!
        {
            leftPaddleAcross -= paddleSpeed;
            rightPaddleAcross += paddleSpeed;
        }

        return true;
    }

    public void draw(Canvas myCanvas, Paint myPaint) {
        myCanvas.drawRGB(20, 20, 20);
        myPaint.setARGB(200,225, 247, 245);

        myCanvas.drawRect(new Rect(ballAcross,ballUp,ballAcross + ball,ballUp + ball),
                myPaint);

        myCanvas.drawRect(new Rect(leftPaddleAcross, leftPaddleUp, leftPaddleAcross + paddleLength,
                leftPaddleUp + paddleThick), myPaint); //top bat
        myCanvas.drawRect(new Rect(rightPaddleAcross, rightPaddleUp, rightPaddleAcross + paddleLength,
                rightPaddleUp + paddleThick), myPaint); //bottom bat

    }
}

