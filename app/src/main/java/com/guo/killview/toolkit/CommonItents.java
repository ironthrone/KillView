package com.guo.killview.toolkit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2016/12/7.
 */

public class CommonItents {
    public static void call(Context context, String number) {
        if (number == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + number);
        intent.setData(data);
        context.startActivity(intent);
    }
}
