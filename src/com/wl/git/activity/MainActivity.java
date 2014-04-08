/**
 * @title MainActivity.java
 * @package com.wl.git.activity
 * @author kervin
 * @version V1.0
 * created 2014-3-6
 */
package com.wl.git.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.wl.git.R;

/**
 * @Description: 
 * @author wangliu
 * @created 2014-3-6 ÏÂÎç09:53:12
 * @version 1.0
 */

public class MainActivity extends TabActivity {
	private TabHost tabHost;
	private Button firstButton,secondButton,thirdButton,forthButton;
	private TextView tv_title;
	private RadioGroup radioGroup;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	public void leftButtonClicked(View view){
		Intent intent = new Intent(this, ToolBoxActivity.class);
		startActivity(intent);
	}
	
	private void initView(){
		tv_title = (TextView)findViewById(R.id.bar_title);
		tabHost = getTabHost();
		firstButton = (Button)findViewById(R.id.tab_one);
		secondButton = (Button)findViewById(R.id.tab_two);
		thirdButton = (Button)findViewById(R.id.tab_three);
		forthButton = (Button)findViewById(R.id.tab_four);
		radioGroup = (RadioGroup)findViewById(R.id.radio_group);
		
		tabHost.addTab(tabHost.newTabSpec("First").setIndicator("First").setContent(new Intent(getApplicationContext(), FirstTabActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("Second").setIndicator("Second").setContent(new Intent(getApplicationContext(), FirstTabActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("Third").setIndicator("Third").setContent(new Intent(getApplicationContext(), FirstTabActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("Forth").setIndicator("Forth").setContent(new Intent(getApplicationContext(), FirstTabActivity.class)));	
		radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
		
	}
	
	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.tab_one:
				tabHost.setCurrentTabByTag("First");
				tv_title.setText("ScrollListView");
				break;
			case R.id.tab_two:
				tabHost.setCurrentTabByTag("Second");
				tv_title.setText("ScrollListView");				
				break;
			case R.id.tab_three:
				tabHost.setCurrentTabByTag("Third");
				tv_title.setText("ScrollListView");
				break;
			case R.id.tab_four:
				tabHost.setCurrentTabByTag("Forth");
				tv_title.setText("ScrollListView");
				break;

			default:
				break;
			}
			
		}
	};
	

}
