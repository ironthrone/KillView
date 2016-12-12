package com.guo.killview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/12/12.
 */

public class BounceScrollView extends ScrollView {
    private static final float MOVE_FACTOR = 0.5f;
    private View mContentView;
    private float mLastTouchY;
    private boolean mOverScrollDown;
    private boolean mOverScrollUp;
    private boolean mIsMove;
    private float mOriginalY;

    public BounceScrollView(Context context) {
        super(context);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(mContentView == null) return;
        mOriginalY = getY();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionMask = ev.getActionMasked();
        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:

                mOverScrollUp = canOverScrollUp();
                mOverScrollDown = canOverScrollDown();
                mLastTouchY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                if (!mOverScrollUp && !mOverScrollDown) {
                    mLastTouchY = ev.getY();
                    mOverScrollUp = canOverScrollUp();
                    mOverScrollDown = canOverScrollDown();
                }

                float newY = ev.getY();
                float deltaY = newY - mLastTouchY;
                mLastTouchY = newY;

                boolean shouldMove = mOverScrollUp && deltaY > 0 || mOverScrollDown && deltaY < 0 || (mOverScrollDown || mOverScrollUp);
                if (shouldMove) {
                    int offsetY = (int) (deltaY * MOVE_FACTOR);
                    mContentView.setY(mContentView.getY() + offsetY);
                    mIsMove = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                if (mIsMove) {

                    mContentView.animate().y(mOriginalY).start();
                    mOverScrollDown = false;
                    mOverScrollUp = false;
                    mIsMove = false;
                }

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean canOverScrollUp() {
        return getScrollY() == 0 | mContentView.getHeight() < getHeight() + getScrollY();
    }

    private boolean canOverScrollDown() {
        return  getHeight() + getScrollY() >=   mContentView.getHeight() ;
    }
}
