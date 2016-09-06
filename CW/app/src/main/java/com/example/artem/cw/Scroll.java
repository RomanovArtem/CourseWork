package com.example.artem.cw;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Artem on 06.09.2016.
 */
public class Scroll extends View
{
    private Bitmap image = null;

    private final GestureDetector gestureDetector;

    public Scroll(Context context, Bitmap image) {
        super(context);
        gestureDetector = new GestureDetector(context, new MyGestureListener());

    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if (gestureDetector.onTouchEvent(event)) return true;
        return true;
    }
    /**
     * Created by Artem on 06.09.2016.
     */
    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onScroll (MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
        {
            scrollBy((int)distanceX, (int) distanceY);
            return true;
        }
    }
}
