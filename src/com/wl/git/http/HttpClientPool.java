/**
 * @title HttpClientPool.java
 * @package com.wl.git.http
 * @author kervin
 * @version V1.0
 * created 2014-3-17
 */
package com.wl.git.http;

/**
 * @Description: 
 * @author wangliu
 * @created 2014-3-17 ÏÂÎç07:26:00
 * @version 1.0
 */

public class HttpClientPool {
	public static final String BASE_URL = "http://www.jbxx.org/api/index.php";
	public volatile static HttpClientPool instance;
	protected static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	
	private HttpClientPool(){};
	
    public static HttpClientPool getInstance(){
		if (null==instance) {
			synchronized (HttpClientPool.class) {
				if (null==instance) {
					instance = new HttpClientPool();
				}
				
			}
						
		}
		return instance;
	}
	
	public  void doHttpFirstTab(String userid,String fid,String page,String count,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "bbs");
		params.put("action", "getbbslist");
		params.put("userid", userid);
		params.put("fid", fid);
		params.put("page", page);
		params.put("count", count);
		asyncHttpClient.get(BASE_URL,params, handler);
		
	}
	
	

}
