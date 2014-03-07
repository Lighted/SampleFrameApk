/**
 * @title MainActivity.java
 * @package com.wl.git.activity
 * @author kervin
 * @version V1.0
 * created 2014-3-6
 */
package com.wl.git.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wl.git.R;

/**
 * @Description: 
 * @author wangliu
 * @created 2014-3-6 ÏÂÎç09:53:12
 * @version 1.0
 */

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void leftButtonClicked(View view){
		Intent intent = new Intent(this, ToolBoxActivity.class);
		startActivity(intent);
	}
	

}
