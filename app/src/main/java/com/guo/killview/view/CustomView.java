package com.guo.killview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.guo.killview.R;


/**
 * Created by Administrator on 2016/11/18.
 */
public class CustomView extends TextView {
    private static final String TAG = CustomView.class.getSimpleName();

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        int start = a.getInt(R.styleable.CustomView_start, 0);
        //直接使用attr定义属性不能生效
//        int star = Integer.parseInt(attrs.getAttributeValue(null,"star"));
//        int star = attrs.getAttributeIntValue(R.attr.star,0);
        a.recycle();
    }

}
