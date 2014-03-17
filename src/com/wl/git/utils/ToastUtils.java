package com.wl.git.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @Description: ��ʾ֪ͨ����
 * @author wangliu
 * @created 2014-3-17 ����10:15:14
 * @version 1.0
 */
public class ToastUtils extends Toast {
	
	public ToastUtils(Context context) {
		super(context);
	}
	

	
	/**
	 * ��ʱ����ʾToast
	 * @param context
	 * @param msg
	 */
	public static void showShort(Context context, int msgId) {
		Toast.makeText(context, context.getResources().getText(msgId), Toast.LENGTH_SHORT).show();
	}
	/**
	 * ��ʱ����ʾToast  
	 * @param context
	 * @param msg
	 */
	public static void showLong(Context context, int msgId) {
		Toast.makeText(context, context.getResources().getText(msgId), Toast.LENGTH_LONG).show();
	}
	
}
