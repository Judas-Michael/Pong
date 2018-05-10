package com.judas.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.logging.Handler;

/**
 * Created by judas on 5/10/2018.
 */

public class GameThread extends Thread { //this class connects directly to the thread method and defines it

    private SurfaceHolder mySurfaceHolder; //this class needs its own holder as well
    private Paint myPaint; //THIS IS So COOL This is a method android offers that stores design information for your application
    private GameState newGameState;

    public GameThread(SurfaceHolder surfaceHolder, Context context, Handler myHandler)
    {
        mySurfaceHolder = surfaceHolder;
        myPaint = new Paint();
        newGameState = new GameState();
    }

    @Override
    public void run() {
        while(true)
        {
            Canvas myCanvas = mySurfaceHolder.lockCanvas(); //this draws your paint option on your surface
            newGameState.update();
            newGameState.draw(myCanvas,myPaint);
            mySurfaceHolder.unlockCanvasAndPost(myCanvas);
        }
    }

    public GameState getGameState() {
        return newGameState;
    }
}
