package com.guo.killview.toolkit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by Administrator on 2016/8/29.
 */
public class IntentHelper {
    public static Intent generateCaptureIntent(Context context, String tempPath) {

            Intent capture = new Intent();
            capture.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri cameraUri = Uri.fromFile(new File(tempPath));
            capture.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            return capture;
    }

    public static Intent generateSelectImageIntent() {
        Intent selectImage = new Intent();
        selectImage.setAction(Intent.ACTION_GET_CONTENT);
        selectImage.addCategory(Intent.CATEGORY_OPENABLE);
        selectImage.setType("image/*");
        return selectImage;
    }

    private static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static String getExternalImagePath() {
        return  Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xichengtemp.jpg";
    }

}
