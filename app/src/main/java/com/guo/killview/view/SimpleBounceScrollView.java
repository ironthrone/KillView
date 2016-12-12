package com.guo.killview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ScrollView;

/**
 * There is a problem
 */
public class SimpleBounceScrollView extends ScrollView {

    private Interpolator mInterpolator;
    private int mMaxBounceDistance = 400;
    private long mStartTime;

    public SimpleBounceScrollView(Context context) {
        this(context,null);
    }

    public SimpleBounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mInterpolator = new BounceInterpolator();
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    private float mCurrentOverScroll;
            float oldBounceDistance;
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (scrollY > 0) {
            oldBounceDistance = 0;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
        } else if (deltaY < 0){

//            long elapsedTime = AnimationUtils.currentAnimationTimeMillis() - mStartTime;
//            float interpolation = mInterpolator.getInterpolation(oldBounceDistance/mMaxBounceDistance);
//            interpolation = Math.min(1, interpolation);
//            oldBounceDistance = (mMaxBounceDistance * interpolation);
            oldBounceDistance += deltaY;
            deltaY = (int) (deltaY * (1 - oldBounceDistance / mMaxBounceDistance));

        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxBounceDistance, isTouchEvent);
    }
}
