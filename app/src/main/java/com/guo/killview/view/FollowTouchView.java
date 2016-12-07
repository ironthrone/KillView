package com.guo.killview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/7/12.
 */
public class FollowTouchView extends View {
    public FollowTouchView(Context context) {
        super(context);
    }

    public FollowTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float oldY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                oldY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float newY = event.getRawY();

                setY(getY() + newY - oldY);
                oldY = newY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


}
