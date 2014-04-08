package com.wl.git.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends Activity {

	private ProgressDialog progressDialog;
	public static BaseActivity baseActivity = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		baseActivity = this;
		}
	

	
	/**
	 * 显示等待框
	 */
	protected void showProgress() {
		showProgress("请稍候", "努力加载中,请稍后");
	}

	protected void shwProgress(String str) {
		showProgress(str);
	}
	
	/**
	 * 显示等待框
	 * 
	 * @param title
	 * @param message
	 */
	protected void showProgress(String title, String message) {
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	protected void showProgress(String message) {
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(message);
		progressDialog.show();
	}
	
	/**
	 * 取消等待框
	 */
	protected void dismissProgress() {
		if (progressDialog != null) {
			try {
				progressDialog.dismiss();
			} catch (Exception e) {

			}
		}
	}
}
