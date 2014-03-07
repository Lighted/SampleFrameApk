/**
 * @title ToolBoxActivity.java
 * @package com.wl.git.activity
 * @author kervin
 * @version V1.0
 * created 2014-3-7
 */
package com.wl.git.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.wl.git.R;

/**
 * @Description: 
 * @author wangliu
 * @created 2014-3-7 ÉÏÎç09:40:11
 * @version 1.0
 */

public class ToolBoxActivity extends Activity implements OnClickListener {
	
	private TextView tv1,tv2,tv3;	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		window.setGravity(Gravity.TOP|Gravity.LEFT);
		setContentView(R.layout.activity_toolbox);
		initView();
	}
	private void initView(){
		tv1 = (TextView)findViewById(R.id.imagecache);
		tv2 = (TextView)findViewById(R.id.downloadmanage);
		tv3 = (TextView)findViewById(R.id.other);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId();
		Intent intent = new Intent();
		switch (viewId) {
		case R.id.imagecache:
			intent.setClass(this, ImageCacheDemoActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.downloadmanage:
			intent.setClass(this, DownloadManageActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.other:
			intent.setClass(this, ImageCacheDemoActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
		
	}

	/*@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {		
		// TODO Auto-generated method stub
		finish();
		return true;
	}
	*/
	
	
	
	

}
