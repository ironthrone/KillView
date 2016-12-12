package com.guo.killview.test;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;

import com.guo.killview.R;

public class BounceActivity extends AppCompatActivity{

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
//        BounceScroller scroller = ((BounceScroller) findViewById(R.id.scrollView));
//        scroller.enableHeader(true);
//        ScrollView scroller = ((ScrollView) findViewById(R.id.scrollView));
//
//        OverScrollDecoratorHelper.setUpOverScroll(scroller);
    }

}
