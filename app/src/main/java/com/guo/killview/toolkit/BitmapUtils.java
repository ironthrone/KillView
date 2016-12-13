package com.guo.killview.toolkit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class BitmapUtils {
	/** 
	 * bitmapתΪbase64 
	 * @param bitmap 
	 * @return 
	 */  
	public static String bitmapToBase64(Bitmap bitmap) {
		  
	    String result = null;
	    ByteArrayOutputStream baos = null;
	    try {  
	        if (bitmap != null) {  
	            baos = new ByteArrayOutputStream();
	            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	  
	            baos.flush();  
	            baos.close();  
	  
	            byte[] bitmapBytes = baos.toByteArray();  
	            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
	        }  
	    } catch (IOException e) {
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            if (baos != null) {  
	                baos.flush();  
	                baos.close();  
	            }  
	        } catch (IOException e) {
	            e.printStackTrace();  
	        }  
	    }  
	    return result;  
	}

	public static Bitmap getCompressBitmap(File file, int targetW, int targetH) {
		if (file == null) {
			throw new IllegalArgumentException("file can not be null");
		}
		if (targetH <= 0 || targetW <= 0) {
			throw new IllegalArgumentException("targetW or targetH can not below zero");

		}
		BitmapFactory.Options bfOptions = new BitmapFactory.Options();
		bfOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), bfOptions);
		int photoW = bfOptions.outWidth;
		int photoH = bfOptions.outHeight;
		if (photoH <= 0 || photoW <= 0) {
			LogUtil.d("getCompressBitmap", "width or height is below zero,photo is broken");
			return null;
		}
		int scaleFactor = calculateInSampleSize(bfOptions, targetW, targetH);

		bfOptions.inJustDecodeBounds = false;
		bfOptions.inSampleSize = scaleFactor;
		bfOptions.inPurgeable = true;
		return BitmapFactory.decodeFile(file.getAbsolutePath(), bfOptions);
	}

	public static int calculateInSampleSize(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) >= reqHeight
					&& (halfWidth / inSampleSize) >= reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}


	public static Bitmap comp(Bitmap image, int width, int height) {
	      
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    if( baos.toByteArray().length / 1024>1024) {//�ж����ͼƬ����1M,����ѹ������������ͼƬ��BitmapFactory.decodeStream��ʱ���    
	        baos.reset();//����baos�����baos  
	        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//����ѹ��50%����ѹ��������ݴ�ŵ�baos��
	    }  
	    ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
	    BitmapFactory.Options newOpts = new BitmapFactory.Options();
	    //��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��  
	    newOpts.inJustDecodeBounds = true;  
	    Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
	    newOpts.inJustDecodeBounds = false;  
	    int w = newOpts.outWidth;  
	    int h = newOpts.outHeight;  
	    Log.e("bitmap", "width"+w+"height:"+h);
	    //���������ֻ��Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ  
	    float ww=100f;
	    float hh = 100f;
	    if(width>100){
	    	ww = width;
	    }
	    if(height>100){
	    	hh=height;
	    }
	    //���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ�����ݽ��м��㼴��  
	    int be = 1;//be=1��ʾ������  
	    if (w >=h && w >= ww) {//�����ȴ�Ļ����ݿ�ȹ̶���С����  
	        be = (int) (newOpts.outWidth / ww);  
	    } else if (w <= h && h >= hh) {//����߶ȸߵĻ����ݿ�ȹ̶���С����  
	        be = (int) (newOpts.outHeight / hh);  
	    }  
	    if (be <= 0)  
	        be = 1;  
	    Log.e("bitmap be", be+"");
	    newOpts.inSampleSize = be;//�������ű���  
	    //���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��  
	    isBm = new ByteArrayInputStream(baos.toByteArray());
	    bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
	    return compressImg(bitmap);//ѹ���ñ�����С���ٽ�������ѹ��  
	}  
	  
	/** 
	 * base64תΪbitmap 
	 * @param base64Data 
	 * @return 
	 */  
	public static Bitmap base64ToBitmap(String base64Data) {
	    byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
	    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}
	
	/**
	 * bitmapѹ��
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {
		  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
        int options = 100;
        Log.e("lenght0", baos.toByteArray().length+"");
        while ( baos.toByteArray().length / 1024>100&&options>0) {  //ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��         
            baos.reset();//����baos�����baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//����ѹ��options%����ѹ��������ݴ�ŵ�baos��
            options -= 20;//ÿ�ζ�����10  
        }
        Log.e("lenght1", baos.toByteArray().length+"");
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//��ѹ���������baos��ŵ�ByteArrayInputStream��
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//��ByteArrayInputStream��������ͼƬ
        return bitmap;  
    }
	
	public static Bitmap compressImg(Bitmap image){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
        int options = 50;
        baos.reset();//����baos�����baos
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        if(baos.toByteArray().length / 1024>100){
        	return compressImg(image);
        }else{
        	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//��ѹ���������baos��ŵ�ByteArrayInputStream��
            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        	return bitmap;
        }
	}

	public static Bitmap getRoundBitmap(Bitmap original, int radius){
		Bitmap squareBitmap = getSquareBitmap(original);
		int desLength = 2 * radius;
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(squareBitmap, desLength, desLength, true);
		Bitmap output = Bitmap.createBitmap(desLength, desLength, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(output);
		//设置画笔
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		Rect rect = new Rect(0, 0, desLength, desLength);
		canvas.drawARGB(0,0,0,0);
		canvas.drawCircle(desLength /2 ,desLength/2,desLength/2,paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(scaledBitmap,rect,rect,paint);
		return output;
	}

	public static Bitmap getSquareBitmap(Bitmap bitmap){
		int squareLength = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth();
		int left = bitmap.getWidth() > bitmap.getHeight() ? (bitmap.getWidth() - bitmap.getHeight()) / 2 :0;
		int top = bitmap.getWidth() > bitmap.getHeight() ?  0 :(bitmap.getHeight() - bitmap.getWidth()) / 2;
		return Bitmap.createBitmap(bitmap, left, top, squareLength, squareLength);
	}
}
