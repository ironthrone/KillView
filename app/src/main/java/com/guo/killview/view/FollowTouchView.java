package com.guo.killview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

/**
 * Created by Administrator on 2016/7/12.
 */
public class FollowTouchView extends View {
    private static final int MAX_FLING_Y = 1000;
    private static final int MAX_FLING_X = 1000;
    private static final String TAG = FollowTouchView.class.getSimpleName();
    private float oldX;
    private float oldY = 0;
    private OverScroller mScroller;
    private GestureDetectorCompat mGestureDectector;
    private Paint mPaint;

    public FollowTouchView(Context context) {
        this(context,null);
    }

    public FollowTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mGestureDectector = new GestureDetectorCompat(context, mOnGestureListener);
        mScroller = new OverScroller(context);
        mPaint = new Paint();
    }

    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollBy(((int) distanceX), ((int) distanceY));
//            setX(getX() - distanceX);
//            setY(getY() - distanceY);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (!mScroller.isFinished()) {

            mScroller.forceFinished(true);
            }
            mScroller.fling(getScrollX(), getScrollY(), -((int) velocityX), -((int) velocityY),0,MAX_FLING_X,0,MAX_FLING_Y);
            mScroller.getFinalX();
            ViewCompat.postInvalidateOnAnimation(FollowTouchView.this);

            return true;
        }
    };

        int lastScrollX = 0;
    int lastScrollY = 0;
    @Override
    public void computeScroll() {
//        super.computeScroll();
//        boolean needInvalidate = false;
//
//        if (mScroller.computeScrollOffset()) {
//            Log.d(TAG, "scroller current x : " + mScroller.getCurrX());
//            Log.d(TAG, "scroller current y : " + mScroller.getCurrY());
//            setX(getX() + mScroller.getCurrX());
//            setY(getY() + mScroller.getCurrY());
//            setScrollX(getScrollX() + mScroller.getCurrX());
//            setScrollY(getScrollY() + mScroller.getCurrY());
//            scrollBy(mScroller.getCurrX() - lastScrollX, mScroller.getCurrY()- lastScrollY);
//            lastScrollX = mScroller.getCurrX();
//            lastScrollY = mScroller.getCurrY();
//            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
//            needInvalidate = true;
//        }
//
//        if (needInvalidate) {
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
//        float radius =  Math.min(cx, cy);
        float radius =  100;
        canvas.drawCircle(cx, cy,radius,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = mGestureDectector.onTouchEvent(event);
//        int actionMasked = event.getActionMasked();
//        switch (actionMasked) {
//            case MotionEvent.ACTION_DOWN:
//                oldY = event.getRawY();
//                oldX = event.getRawX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float newY = event.getRawY();
//                float newX = event.getRawX();
//
//                setY(getY() + newY - oldY);
//                setX(getX() + newX - oldX);
//                oldX = newX;
//                oldY = newY;
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
        return handled | super.onTouchEvent(event);
    }



}
