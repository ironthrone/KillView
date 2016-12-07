package com.guo.killview.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ScrollableView extends View implements GestureDetector.OnGestureListener{
    private Context mContext;
    private GestureDetectorCompat gestureDetector;

    public ScrollableView(Context context) {
        this(context, null);
    }

    public ScrollableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        gestureDetector = new GestureDetectorCompat(context, this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
//        scrollBy((int) event.getX(),0);
        //why change this to return true,the onLongPress not called
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(mContext, "onDown", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(mContext, "onShowPress", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(mContext, "onSingleTapUp", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Toast.makeText(mContext, "onScroll", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(mContext, "onLongPress", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(mContext, "onFling", Toast.LENGTH_SHORT).show();

        return true;
    }
}
