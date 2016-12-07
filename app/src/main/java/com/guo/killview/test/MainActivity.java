package com.guo.killview.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.guo.killview.R;

//import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity {

    private InputMethodManager inputMethodManager;
    private EditText edit;
    private ImageView imageView;
    private ImageView imageZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        imageView = (ImageView) findViewById(R.id.image);
        imageZoom = (ImageView) findViewById(R.id.image_zoom);
//        PhotoViewAttacher attacher = new PhotoViewAttacher(imageZoom);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    private ImageView.ScaleType[] scaleTypes = {};
    public void click(View view) {
        switch (view.getId()) {
            case R.id.show:
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                break;
            case R.id.hide:
                inputMethodManager.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                break;
            case R.id.scale:
                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                inputMethodManager.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                break;
        }
    }
}
