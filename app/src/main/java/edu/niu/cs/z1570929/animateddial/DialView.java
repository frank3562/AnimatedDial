package edu.niu.cs.z1570929.animateddial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by z1570929 on 4/19/2016.
 */
public class DialView extends View
{
    //Declare radian angle and paint object
    private float angle;
    private Paint paint;

    public DialView(Context context)
    {
        super(context);

        //set angle
        angle = 0;

        //create the paint object
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(75);


    }//end of constructor

    //Override the onDraw method in the Canvas object to create a custom view
    @Override
    protected void onDraw(Canvas canvas)
    {

        //set background color for the canvas
        canvas.drawRGB(248, 232, 198);

        //calculate the canvas width and height
        int canvasWidth = getMeasuredWidth(),
            canvasHeight = getMeasuredHeight(),
            radius;

        //check for landscape, set radius for landscape version
        if( canvasWidth > canvasHeight )
            radius = canvasHeight / 2;
        else //portrait version, set radius for portriat version
            radius = canvasWidth / 2;

        //update angle
        angle += 1;
        if(angle > 360)
            angle = 0;

        //draw the line
        float radians = (float)(angle * (180/ Math.PI)),
            startX = canvasWidth / 2,
            startY = canvasHeight / 2,
            stopX = (float)(startX + radius * Math.sin(radians)),
            stopY = (float)(startY - radius * Math.cos(radians));

        //set up color
        paint.setColor(Color.rgb(132, 175, 166));
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }//end of onDraw

    public void update()
    {
        invalidate();
    }//end of update


}//end of DialView class
