/*
Programmer: Franklin Adams
ZID: Z1570929
Date: 4/19/2016
Program name: AnimatedDial
Function: This program will show an animated dial using threads in MainActivity.java and
DialView.java to Paint a dial on the Canvas. A TextView of "Hello world" is left on activity.main
to show that Paint called in DialView.java will override the overlay on the Canvas in
activity_main.xml
*/

package edu.niu.cs.z1570929.animateddial;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    //Declare Thread and DialView variables
    private Thread animationThread;
    private DialView dialView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //create DialView object
        dialView = new DialView(this);

        //Change focus to the dialView
        setContentView(dialView);

        //Create a new Thread for animationThread
        animationThread = new Thread(runningAnimation);
        animationThread.start();

    }//end of OnCreate

    //Override the run() method with a delay to slow the animation of the clock dial
    private Runnable runningAnimation = new Runnable()
    {
        //Slow down the animation by 200ms
        private static final int DELAY = 200;

        @Override
        public void run()
        {
            while(true)
            {
                try
                {
                    Thread.sleep(DELAY);

                    threadHandler.sendEmptyMessage(0);

                }//end of try block
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }//end of catch block
            }//end of while
        }//end of run
    };//end of runningAnimation

    //Use a handler to display the new dialview's dial position
    private Handler threadHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            dialView.update();
        }//end of handleMessage(Message msg)

    };//end of threadHandler

    //remove any pending posts of runningAnimation during onPause lifecycle
    @Override
    protected void onPause()
    {
        super.onPause();
        threadHandler.removeCallbacks(runningAnimation);
    }//end of onPause()

}//end of MainActivity
