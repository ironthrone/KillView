package com.guo.killview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/12/13.
 */

public class ResponseView extends View {
    private SparseArray<Touch> mTouches = new SparseArray<>();
    private static final int CIRCLE_RADIUS_DP = 100;
    private float mCircleRadius;
    private Paint mPaint;
    private boolean mHasTouch;
    public final int[] COLORS = {
            0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444,
            0xFF0099CC, 0xFF9933CC, 0xFF669900, 0xFFFF8800, 0xFFCC0000
    };

    public ResponseView(Context context) {
        this(context,null);
    }

    public ResponseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        float density = getResources().getDisplayMetrics().density;
        mCircleRadius = CIRCLE_RADIUS_DP * density;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {

                int actionIndex = event.getActionIndex();
                int actionId = event.getPointerId(actionIndex);
                Touch touch = new Touch();
                touch.setTouch(event.getX(actionIndex), event.getY(actionIndex), event.getPressure(actionIndex));
                mTouches.put(actionId, touch);
                mHasTouch = true;
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {

                int actionIndex = event.getActionIndex();
                int actionId = event.getPointerId(actionIndex);
                Touch touch = new Touch();
                touch.setTouch(event.getX(actionIndex), event.getY(actionIndex), event.getPressure(actionIndex));
                mTouches.put(actionId, touch);
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                int actionId = event.getPointerId(event.getActionIndex());
                mTouches.remove(actionId);
                break;
            }
            case MotionEvent.ACTION_UP: {
                int actionId = event.getPointerId(event.getActionIndex());
                mTouches.remove(actionId);
                mHasTouch = false;
                break;
            }
            case MotionEvent.ACTION_MOVE:{

                for (int index = 0; index < event.getPointerCount(); index++) {
                    int actionId = event.getPointerId(index);
                    Touch touch = mTouches.get(actionId);
                    touch.setTouch(event.getX(index), event.getY(index), event.getPressure(index));
                }
                break;
            }
        }
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mHasTouch) {
            for (int index = 0; index < mTouches.size(); index++) {
                mPaint.setColor(COLORS[index % COLORS.length]);
                Touch touch = mTouches.get(mTouches.keyAt(index));
                float radius = touch.pressure * mCircleRadius;
                canvas.drawCircle(touch.x, touch.y, radius,mPaint);
            }
        }
        super.onDraw(canvas);
    }

    static class Touch {
        float x;
        float y;
        float pressure;

        public void setTouch(float x, float y, float pressure) {
            this.x = x;
            this.y = y;
            this.pressure = pressure;
        }
    }
}
