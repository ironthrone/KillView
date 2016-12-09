package com.guo.killview.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.guo.killview.R;

public class GestureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
//        ((TextView) findViewById(R.id.textView)).setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.touch_view).scrollBy(100,100);
    }
}
