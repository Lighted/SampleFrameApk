package com.wl.git.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @Description: 显示通知工具
 * @author wangliu
 * @created 2014-3-17 上午10:15:14
 * @version 1.0
 */
public class ToastUtils extends Toast {
	
	public ToastUtils(Context context) {
		super(context);
	}
	

	
	/**
	 * 短时间显示Toast
	 * @param context
	 * @param msg
	 */
	public static void showShort(Context context, int msgId) {
		Toast.makeText(context, context.getResources().getText(msgId), Toast.LENGTH_SHORT).show();
	}
	/**
	 * 长时间显示Toast  
	 * @param context
	 * @param msg
	 */
	public static void showLong(Context context, int msgId) {
		Toast.makeText(context, context.getResources().getText(msgId), Toast.LENGTH_LONG).show();
	}
	
}
