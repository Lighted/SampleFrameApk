/**
 * @title FirstTabActivity.java
 * @package com.wl.git.activity
 * @author kervin
 * @version V1.0
 * created 2014-3-16
 */
package com.wl.git.activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.wl.git.R;
import com.wl.git.adapter.SchoolNoticeAdapter;
import com.wl.git.bean.GlobalVariable;
import com.wl.git.bean.HttpCache;
import com.wl.git.bean.HttpCache.HttpCacheListener;
import com.wl.git.bean.HttpRequest;
import com.wl.git.bean.HttpResponse;
import com.wl.git.bean.SchoolNoticeBean;
import com.wl.git.http.HttpClientPool;
import com.wl.git.http.JsonHttpResponseHandler;
import com.wl.git.utils.CacheManager;
import com.wl.git.utils.ToastUtils;
import com.wl.git.view.DropDownListView;
import com.wl.git.view.DropDownListView.OnDropDownListener;

/**
 * @Description: 
 * @author wangliu
 * @created 2014-3-16 上午11:39:34
 * @version 1.0
 */

public class SecondTabActivity extends BaseActivity {
	private DropDownListView listView;
	private SchoolNoticeAdapter adapter = null;
	private HttpResultHandler handler;
	
	
	private LinkedList<SchoolNoticeBean> linkedList = new LinkedList<SchoolNoticeBean>();
	private String fid = "46";	
	private String page = "1";
	private String count = "10";	
	private int moreDataCount = 0;
	public int MORE_DATA_MAX_COUNT = 3;
	private HttpCache httpCache;
	private Map<String, String> params = null;
	private Context context = null;
	
    protected static final int SUCCESS_MESSAGE = 0;
    protected static final int FAILURE_MESSAGE = 1;
    protected static final int START_MESSAGE = 2;
    protected static final int FINISH_MESSAGE = 3;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_refresh_layout);
		initView();
		
		
	}
	private void initData(){
		params = new HashMap<String, String>();;
		params.put("module", "bbs");
		params.put("action", "getbbslist");
		params.put("userid", GlobalVariable.userID);
		params.put("fid", fid);
		params.put("page", page);
		params.put("count", count);
		context = getApplicationContext();
        // get the singleton instance of HttpCache
        httpCache = CacheManager.getHttpCache(context);
        HttpRequest httpRequest = new HttpRequest(HttpClientPool.BASE_URL);
        httpRequest.setParasMap(params);
        httpCache.httpGet(httpRequest, new HttpCacheListener() {

			@Override
			protected void onPreGet() {
				// TODO Auto-generated method stub
				super.onPreGet();
				showProgress();
			}

			@Override
			protected void onPostGet(HttpResponse httpResponse,
					boolean isInCache) {
				// TODO Auto-generated method stub
				super.onPostGet(httpResponse, isInCache);
			}
			
        });
	}
	
	// Interface to AsyncHttpRequest
    void sendResponseMessage(HttpResponse response) {
       
        String responseBody = null;
        responseBody = response.getResponseBody();
        if(response.getResponseCode() >= 300) {
            sendFailureMessage(responseBody);
        } else {
            sendSuccessMessage(responseBody);
        }
    }
    protected void sendFailureMessage(String responseBody) {
        sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]{responseBody}));
    }
    protected void sendSuccessMessage(String responseBody) {
        sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]{responseBody}));
    }  
    
    protected Message obtainMessage(int responseMessage, Object response) {
        Message msg = null;
        if(handler != null){
            msg = this.handler.obtainMessage(responseMessage, response);
        }else{
            msg = Message.obtain();
            msg.what = responseMessage;
            msg.obj = response;
        }
        return msg;
    }
    
    protected void sendMessage(Message msg) {
        if(handler != null){
            handler.sendMessage(msg);
        } else {
            handler.handleMessage(msg);
        }
    }
    
    
	private void initView(){		
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
	
		
	}
	
	class HttpResultHandler extends JsonHttpResponseHandler {
		private Message message ;
		private String responsecode;
		
		protected void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
		
		

		@Override
		public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode,headers,response);
			try {
	        	responsecode = response.getString("responsecode");
	        	if(responsecode.equals("10")) {
	        		JSONArray l = response.getJSONArray("result");
					int len = l.length();

					for (int i = 0; i < len; i++) {
						JSONObject item = l.getJSONObject(i);
						SchoolNoticeBean schoolBean = new SchoolNoticeBean();
						schoolBean.setId(item.getString("tid"));
						schoolBean.setAuthor(item.getString("author"));
						schoolBean.setContent(item.optString("subject"));
						schoolBean.setPopularity(item.optString("hits"));
						schoolBean.setReplayCount(item.optString("replies"));
						schoolBean.setCreateTime(item.optString("postdate"));
						linkedList.add(schoolBean);						
					}					
					if (linkedList.size()>0) {
						adapter = new SchoolNoticeAdapter(linkedList, SecondTabActivity.this);
						listView.setAdapter(adapter);
						adapter.notifyDataSetChanged();
						
					}
					
	        	} else {
//	        		ToastUtils.showLong(SchoolNoticeListActivity.this, "加载数据失败");
	        	}
				

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onFailure(int statuscode,Header[]headers,Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(statuscode,headers,e, errorResponse);
			shwProgress(getString(R.string.loading_fail));
			
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			showProgress();
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
			
			dismissProgress();
			
		}
		
		
		
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
			return null ;
			
		}

		@Override
		protected void onPostExecute(String[] result) {
			// TODO Auto-generated method stub
			if (isDropdown) {
				SchoolNoticeBean schoolNoticeBean = new SchoolNoticeBean();
				schoolNoticeBean.setContent("下拉加载");
				linkedList.addFirst(schoolNoticeBean);
				adapter.notifyDataSetChanged();
				
				SimpleDateFormat simpledateformat = new SimpleDateFormat("MM-dd HH:mm:ss");
				listView.onDropDownComplete(getString(R.string.update_at) + simpledateformat.format(new Date()));
				
				
			}else {
				moreDataCount++;
				SchoolNoticeBean schoolNoticeBean = new SchoolNoticeBean();
				schoolNoticeBean.setContent("加载");
				linkedList.add(schoolNoticeBean);
				adapter.notifyDataSetChanged();
				if (moreDataCount>=MORE_DATA_MAX_COUNT) {
					listView.setHasMore(false);
				}
				listView.onBottomComplete();
			}
			
			super.onPostExecute(result);
			
			
			
			
		}
		
	}
	
	
	

}
