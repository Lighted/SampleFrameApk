/**
 * @title FirstTabActivity.java
 * @package com.wl.git.activity
 * @author kervin
 * @version V1.0
 * created 2014-3-16
 */
package com.wl.git.activity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.wl.git.R;
import com.wl.git.utils.ToastUtils;
import com.wl.git.view.DropDownListView;
import com.wl.git.view.DropDownListView.OnDropDownListener;

/**
 * @Description: 
 * @author wangliu
 * @created 2014-3-16 ÉÏÎç11:39:34
 * @version 1.0
 */

public class FirstTabActivity extends Activity {
	private DropDownListView listView;
	private String [] arrays = {"just for test","just for test","just for test","just for test","just for test","just for test","just for test","just for test","just for test","just for test","just for test","just for test"};
	private LinkedList<String> linkedList;
	private ArrayAdapter<String> arrayAdapter = null;
	private int moreDataCount = 0;
	public int MORE_DATA_MAX_COUNT = 3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_refresh_layout);
		init();
		
	}
	private void init(){
		
		listView = (DropDownListView)findViewById(R.id.listview);
		listView.setDropDownStyle(true);
		listView.setOnDropDownListener(new OnDropDownListener() {
			
			@Override
			public void onDropDown() {
				// TODO Auto-generated method stub
				new getDataTask(true).execute();
				
			}
		});
		listView.setOnBottomListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new getDataTask(false).execute();
				
			}
		});
		listView.setShowFooterWhenNoMore(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ToastUtils.showShort(getApplicationContext(),R.string.scrolltorefresh);
				
			}			
			
		});	
		linkedList = new LinkedList<String>();
		linkedList.addAll(Arrays.asList(arrays));
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,linkedList);
		listView.setAdapter(arrayAdapter);
		
	}
	
	
	class getDataTask extends AsyncTask<Void, Void, String[]>{
		private boolean isDropdown;

		/**
		 * Constructor Method 
		 * @param b
		 */
		public getDataTask(boolean b) {
			// TODO Auto-generated constructor stub
			isDropdown = b;
		}

		@Override
		protected String[] doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				;
			}
			return arrays;
			
		}

		@Override
		protected void onPostExecute(String[] result) {
			// TODO Auto-generated method stub
			if (isDropdown) {
				linkedList.addFirst("Added after drop down");
				arrayAdapter.notifyDataSetChanged();
				
				SimpleDateFormat simpledateformat = new SimpleDateFormat("MM-dd HH:mm:ss");
				listView.onDropDownComplete(getString(R.string.update_at) + simpledateformat.format(new Date()));
				
				
			}else {
				moreDataCount++;
				linkedList.add("added after drop down");
				arrayAdapter.notifyDataSetChanged();
				if (moreDataCount>=MORE_DATA_MAX_COUNT) {
					listView.setHasMore(false);
				}
				listView.onBottomComplete();
			}
			
			super.onPostExecute(result);
			
			
			
			
		}
		
	}
	

}
