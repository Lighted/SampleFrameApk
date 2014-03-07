package com.wl.git.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.wl.git.R;
import com.wl.git.bean.Constant;
import com.wl.git.bean.GlobalVariable;
import com.wl.git.utils.Utils;
/**
 * 
 * @Description: »¶Ó­Ò³
 * @author wangliu
 * @date 2013-10-22 ÏÂÎç3:10:20 
 * @version V1.0
 */
public class WelcomeActivity extends Activity{

	private ImageView welcome_page;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_welcome);
		initView();
		GlobalVariable.userID = Utils.readData(this, Constant.USER_ID, 1);
		createAnimation();
	}
	
	public void initView() {
		
		welcome_page = (ImageView) this.findViewById(R.id.welcome_page);
	}
	
	private void createAnimation() {
		// TODO Auto-generated method stub
		AlphaAnimation alpAni = new AlphaAnimation(0.0f, 1.0f);
		alpAni.setDuration(2000);
		welcome_page.startAnimation(alpAni);
		alpAni.setAnimationListener(new MyAnimationListener());
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	private class MyAnimationListener implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent();
			intent.setClass(WelcomeActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}
	}

}