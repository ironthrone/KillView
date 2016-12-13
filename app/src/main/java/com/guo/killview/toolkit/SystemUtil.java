/**
 *  ClassName: MyApp.java
 *  created on 2015-1-24
 *  Copyrights 2015-1-24 miaoyupei All rights reserved.
 *  site: http://t.qq.com/miaoyupei
 *  email: miaoyupei@yahoo.cn
 */
package com.guo.killview.toolkit;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import com.theaty.appbase.system.BaseApplication;

import java.util.List;

//import com.theaty.xida.base.wu.util.StringUtil;

/**
 * 获取系统信息的工具类
 * 
 * @author miaoyupei
 */
public class SystemUtil {

	/**
	 * 检测当前的网络连接是否可用<br/>
	 * 注意：需要添加权限&lt;uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;
	 * 
	 *            返回值：
	 */
	public static boolean isConnected() {
		boolean flag = false;
		ConnectivityManager connManager = (ConnectivityManager) getAppInstance()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connManager) {
			NetworkInfo info = connManager.getActiveNetworkInfo();
			if (null != info && info.isAvailable()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 检测当前网络连接的类型<br/>
	 * 注意：需要添加权限&lt;uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;
	 * 
	 */
	public static String getNetworkType() {
		int code = -1;
		ConnectivityManager connManager = (ConnectivityManager) getAppInstance()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connManager) {
			State state = connManager.getNetworkInfo(
					ConnectivityManager.TYPE_WIFI).getState();
			if (State.CONNECTED == state) {
				code = ConnectivityManager.TYPE_WIFI;
			} else {
				state = connManager.getNetworkInfo(
						ConnectivityManager.TYPE_MOBILE).getState();
				if (State.CONNECTED == state) {
					code = ConnectivityManager.TYPE_MOBILE;
				}
			}
		}
		switch (code) {
		case 0:
			return "GPRS网络";
		case 1:
			return "WIFI网络";
		case -1:
			return "网络不可用";
		default:
			return "检测失败！";
		}
	}

	public static void checkInternet(){
		if (getAppInstance() != null
				&& !SystemUtil.isConnected()) {
			ToastUtil.showToast("请检查网络!");
		}
	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings",
				"com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}


	/**
	 * 离开程序
	 */
	private void quit(){
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		System.exit(0);

	}

	/**
	 * 返回当前程序版本代码,如:1
	 * 
	 *            返回值： 当前程序版本代码
	 */
	public static int getAppVersionCode() {
		int versionCode = -1;
		try {
			Context mApp = getAppInstance();
			PackageManager pm = mApp.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(mApp.getPackageName(), 0);
			versionCode = pi.versionCode;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 返回当前程序版本名,如:1.0.1
	 * 
	 *            返回值： 当前程序版本名
	 */
	public static String getAppVersionName() {
		String versionName = "";
		try {
			Context mApp = getAppInstance();
			PackageManager pm = mApp.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(mApp.getPackageName(), 0);
			versionName = pi.versionName;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 安装指定的APK文件，主要用于本应用程序的更新
	 * 
	 * @param context
	 * @param apk
	 *            apk文件的全路径名
	 */
//	public static void installAPK(Context context, String apk) {
//		if(StringUtil.isEmpty(apk)){
//			return;
//		}
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		intent.setDataAndType(Uri.fromFile(new File(apk)),
//				"application/vnd.android.package-archive");
//		context.startActivity(intent);
//	}

	/**
	 * whether application is in background
	 * <ul>
	 * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
	 * </ul>
	 * 
	 * @return if application is in background return true, otherwise return
	 *         false
	 */
	public static boolean isApplicationInBackground() {
		Context mApp = getAppInstance();
		ActivityManager am = (ActivityManager) mApp
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(1);
		if (taskList != null && !taskList.isEmpty()) {
			ComponentName topActivity = taskList.get(0).topActivity;
			if (topActivity != null
					&& !topActivity.getPackageName().equals(
							mApp.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	private static Context getAppInstance() {
		return BaseApplication.getInstance();
	}
}
