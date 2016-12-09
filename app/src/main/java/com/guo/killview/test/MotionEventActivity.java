package com.guo.killview.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.guo.killview.R;

public class MotionEventActivity extends AppCompatActivity {

    private static final String TAG = MotionEventActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        printSamples(event);
        return true;
    }


    void printSamples(MotionEvent ev) {
        final int historySize = ev.getHistorySize();
        final int pointerCount = ev.getPointerCount();
        Log.d(TAG, "historySize : " + historySize);
        Log.d(TAG, "pointerCount : " + pointerCount);
        for (int h = 0; h < historySize; h++) {
            Log.d(TAG, "At time :" + ev.getHistoricalEventTime(h));
            for (int p = 0; p < pointerCount; p++) {
                Log.d(TAG, String.format("  pointer %d: (%f,%f)",
                        ev.getPointerId(p), ev.getHistoricalX(p, h), ev.getHistoricalY(p, h)));

            }
        }
        Log.d(TAG, String.format("At time %d:", ev.getEventTime()));
        for (int p = 0; p < pointerCount; p++) {
            Log.d(TAG, String.format("  pointer %d: (%f,%f)",
                    ev.getPointerId(p), ev.getX(p), ev.getY(p)));
        }
    }
}
