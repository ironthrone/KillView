package com.guo.killview.toolkit;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.theaty.appbase.system.BaseApplication;

/**
 * 常用单位转换的辅助类
 * 
 * @author blueam
 * 
 */
public class DpUtil {

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(float pxValue) {
		DisplayMetrics metrics = BaseApplication.getInstance().getResources().getDisplayMetrics();
		return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,pxValue,metrics);

	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(float dipValue) {
		float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(float pxValue) {
		final float fontScale = getAppInstance().getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(float spValue) {
		final float fontScale = getAppInstance().getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	private static Context getAppInstance() {
		return BaseApplication.getInstance();
	}
}
