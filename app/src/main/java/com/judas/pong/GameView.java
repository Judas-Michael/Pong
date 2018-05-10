package com.judas.pong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by judas on 5/9/2018.
 */

public class GameView  extends SurfaceView implements SurfaceHolder.Callback { //The surface view allows user interaction within the space
    //the class "GameView" is being defined as a "SurfaceView"

    private Thread thread; //creates a thread named thread
    //a thread is a running process within an application

    public GameView(Context context, AttributeSet attrs) { //this method recieves context and rules (attributes) to set up the view!
        super(context, attrs); //super is a java word that means to look to the 'parent method' for the answer!

        SurfaceHolder myHolder = getHolder(); //myHolder is a surface holder object
        myHolder.addCallback(this); //this calls the holder into action
        setFocusable(true); //this tells the application that the user can pick different parts of the view to interact with
        // is setFocusable necessary? SlackOverflow suggested yes, but the definition implies that it's automatically set to true. Try to delete this later

        thread = new GameThread(myHolder, context, new Handler() {
            @Override
            public void publish(LogRecord logRecord) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        }); //initiates thread by contacting GameThread method and sending it the necessary information
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder myHolder) {
        thread.stop(); //ends thread (game)
    }

    @Override
    public boolean onKeyDown(int buttonID, KeyEvent popUp) {
        return thread.getGameState().keyPressed(buttonID, popUp);
    }

    @Override
    public void surfaceCreated(SurfaceHolder myHolder) { //creates view
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder myHolder, int format, int width, //This is a necessary method in the callback feature. If something happens it allows the app to reformat
                               int height) {

    }




}