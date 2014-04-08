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
	 * ��ʾ�ȴ���
	 */
	protected void showProgress() {
		showProgress("���Ժ�", "Ŭ��������,���Ժ�");
	}

	protected void shwProgress(String str) {
		showProgress(str);
	}
	
	/**
	 * ��ʾ�ȴ���
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
	 * ȡ���ȴ���
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
