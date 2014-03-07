package com.wl.git.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @类描述: TODO
 * @文件名: ToastUtils.java
 * @包名路径: com.ehui.ibmclub.utils
 * @创建时间 2013-1-4 下午5:08:54
 * @author 耿松	
 * @version V1.0
 */
public class ToastUtils extends Toast {
	
	public ToastUtils(Context context) {
		super(context);
	}
	
	/**
	 * 是否显示    false 表示上线阶段，true 表示开发阶段
	 */
	private static final boolean  isShow = true;
	
	/**
	 * 显示测试Toast,不用show哦
	 * @param context
	 * @param msg
	 * @param duration
	 */
	public static  void toast(Context context, String msg) {
		if(isShow){
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 短时间显示Toast
	 * @param context
	 * @param msg
	 */
	public static void showShort(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 长时间显示Toast  
	 * @param context
	 * @param msg
	 */
	public static void showLong(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	
}
