package com.guo.killview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntegerRes;
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
    private static final String TAG = FollowTouchView.class.getSimpleName();
    private OverScroller mScroller;
    private GestureDetectorCompat mGestureDectector;
    private Paint mPaint;

    public FollowTouchView(Context context) {
        this(context, null);
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
            mScroller.forceFinished(true);
            ViewCompat.postInvalidateOnAnimation(FollowTouchView.this);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollBy(((int) distanceX), ((int) distanceY));
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            mScroller.forceFinished(true);
            mScroller.fling(getScrollX(), getScrollY(), -((int) velocityX), -((int) velocityY), Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            ViewCompat.postInvalidateOnAnimation(FollowTouchView.this);

            return true;
        }
    };


    @Override
    public void computeScroll() {
        boolean needInvalidate = false;
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            needInvalidate = true;
        }
        if (needInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float radius = 100;
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = mGestureDectector.onTouchEvent(event);
        return handled | super.onTouchEvent(event);
    }


}
