package com.guo.killview.toolkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;


import com.guo.killview.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/16.
 */
public class ImageCompressor {
    private static final int IMAGE_QUALITY = 70;
    private static String PICTURE_DIR_NAME;
    private static final String TAG = ImageCompressor.class.getSimpleName();
    private static final java.lang.String JPEG_FILE_SUFFIX = ".jpg";
    private static final String TEMP_IMAGE_PREFIX = "compress";

    private ImageCompressor() {

    }

    private static ImageCompressor INSTANCE;

    public static ImageCompressor getInstance(Context context) {
        synchronized (ImageCompressor.class) {
            if (INSTANCE == null) {
                INSTANCE = new ImageCompressor();
                PICTURE_DIR_NAME = context.getResources().getString(R.string.app_name);
            }
            return INSTANCE;
        }
    }

    public File compress(File sourceImage) {
        File compressed = createCompressedFile(sourceImage);
        Bitmap compressedBitmap = null;
        FileOutputStream fos = null;

        if (compressed != null) {
            try {
                compressedBitmap = BitmapUtils.getCompressBitmap(sourceImage, 600, 400);
                fos = new FileOutputStream(compressed);

                if (compressedBitmap != null) {

                    compressedBitmap.compress(Bitmap.CompressFormat.JPEG, IMAGE_QUALITY, fos);
                } else {
                    Log.d(TAG, "compressedBitmap is null");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (compressedBitmap != null) {
                    compressedBitmap.recycle();
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return compressed;
    }

    private File createImageDir() {
        File dir = null;
        if (SDCardUtil.isSDCardEnable()) {
            dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), PICTURE_DIR_NAME);
            if (!dir.mkdir()) {
                if (!dir.exists()) {
                    Log.d(TAG, "create picture dir fail");
                }
            }
        } else {
            Log.d(TAG, "external storage is not available");
        }
        return dir;
    }

//    private File createCompressedFile(File sourceFile) {
//        File albumDir = createImageDir();
//        File file = null;
//        try {
//            file = File.createTempFile(TEMP_IMAGE_PREFIX, JPEG_FILE_SUFFIX, albumDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }

    private File createCompressedFile(File sourceFile) {
        File albumDir = createImageDir();
        String sourceFileName = sourceFile.getName().split("\\.")[0];
        String compressedFileName = sourceFileName + "_compress.jpg";
        File file = null;
        try {
            //very import,infinite loop
            do {
                file = new File(albumDir, compressedFileName);
                Log.d(TAG, "Creating file " + file.getName());
            } while (!file.exists() && !file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
