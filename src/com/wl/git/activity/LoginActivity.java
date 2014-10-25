package com.wl.git.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wl.git.R;
import com.wl.git.bean.Api;
import com.wl.git.bean.Constant;
import com.wl.git.bean.GlobalVariable;
import com.wl.git.utils.ToastUtils;
import com.wl.git.utils.Utils;
/**
 * @Description: 登录
 * @author gengsong
 * @date 2013-10-8 下午2:26:13 
 * @version V1.0
 */
public class LoginActivity extends BaseActivity {

	private EditText userName_EditText;
	private EditText passWord_EditText;
	private CheckBox remember_CheckBox;
	private String user_account = "";
	private String user_password = "";
	private LoginTask mLoginTask = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();	
		if (Utils.readBooleanData(LoginActivity.this, "passwordsaved", false)) {
			user_account = Utils.readStringData(this, Constant.USER_NAME);
			user_password = Utils.readStringData(this, Constant.PASSWORD);
			if(!TextUtils.isEmpty(user_account)) {
				userName_EditText.setText(user_account.toString());
			}
			if(!TextUtils.isEmpty(user_password)) {
				passWord_EditText.setText(user_password.toString());
			}
			loginEvent(null);
		}
		
		
	}
	
	private void initView() {
		userName_EditText = (EditText) this.findViewById(R.id.input_account);
		passWord_EditText = (EditText) this.findViewById(R.id.input_pwd);
		remember_CheckBox = (CheckBox) this.findViewById(R.id.cb_savePwd); 
		/**/
	}
	
	/**
	 * @Title: LoginEvent 
	 * @Description: 登录
	 * @param @param view    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public void loginEvent(View view) {
		int networkType = Utils.network_Identification(this);
		if (0 == networkType) {
			Toast.makeText(this, "当前没有网络,请检查网络再试...", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		
		int loginType = this.loginCheck();

		if (loginType != 0) {
			mLoginTask = new LoginTask(user_account,user_password);
			mLoginTask.execute();
		}
		
		
	}	
	
	
	/**
	 * 本地登录验证
	 * gengsong
	 * @返回1表示手机登录，返回0表示登录失败
	 */
	private int loginCheck() {
		user_account = userName_EditText.getText().toString();
		user_password = passWord_EditText.getText().toString();
		if (TextUtils.isEmpty(user_account)) {
			Toast.makeText(this, "请输入正确的帐号", Toast.LENGTH_LONG).show();
			return 0;
		}
		if (TextUtils.isEmpty(user_password)
				|| user_password.trim().length() < 6
				|| user_password.trim().length() > 32) {
			Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_LONG).show();
			return 0;
		}
		return 1;
	}
	
	/**
	 * 
	 * @Description:记住密码
	 * @author wangliu
	 * @created 2013-12-3 下午03:24:15
	 * @version 1.0
	 */
	
	private void savePassword(String username, String password){
		//如果选择保存密码
		if (remember_CheckBox.isChecked()) {
			Utils.writeStringData(LoginActivity.this,Constant.USER_NAME, username);
			Utils.writeStringData(LoginActivity.this, Constant.PASSWORD, password);
			Utils.writeBooleanData(LoginActivity.this, "passwordsaved", true);
		}
			
	}
	
	

	private class LoginTask extends AsyncTask<Void, Void, Void> {

		private String responseResult = "";
		private int resultCode = -1;
		private String username = "";
		private String password = "";
		private String msg = "";
		
		public LoginTask(String username,String password) {
			this.username = username;
			this.password = password;
		}
		
		@Override
        protected void onPreExecute() {
	        showProgress();
	        super.onPreExecute();
        }
		
		
		@Override
        protected Void doInBackground(Void... arg0) {
			String baseURL = Utils.getBaseURL();
			List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
			requestParams.add(new BasicNameValuePair("module", "user"));
			requestParams.add(new BasicNameValuePair("action", Api.LOGIN_API));
			requestParams.add(new BasicNameValuePair("username", username));
			requestParams.add(new BasicNameValuePair("password",password));
			responseResult = Utils.getData(baseURL, requestParams);
			

			if (!TextUtils.isEmpty(responseResult) &&responseResult.startsWith("\ufeff")) {
				responseResult = responseResult.substring(1);
				try {
					JSONObject jsonObject = new JSONObject(responseResult);
					resultCode = jsonObject.getInt("responsecode");
					msg = jsonObject.getString("msg");
					if (resultCode == 10) {
						JSONObject jsonObject2 = jsonObject.getJSONObject("result");
						GlobalVariable.userID = jsonObject2.getString("uid");
						GlobalVariable.userName = jsonObject2.getString("username");
						GlobalVariable.userEmail = jsonObject2.getString("email");
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					resultCode = -1;
					e.printStackTrace();
				}
			} else {
				resultCode = -1;
			}
			return null;
        }

		@Override
        protected void onPostExecute(Void result) {
	        dismissProgress();	
	        switch (resultCode) {
			case 11:
				ToastUtils.showLong(LoginActivity.this, R.string.login_fail);
				Utils.writeIntData(LoginActivity.this,Constant.USER_NAME,1);
				break;
			case 10:
				savePassword(username, password);
				ToastUtils.showLong(LoginActivity.this, R.string.login_success);
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
				break;

			default:
				break;
			}
	        super.onPostExecute(result);
        }

	}
	
}
