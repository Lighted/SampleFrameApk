package com.wl.git.utils;

import java.io.File;
import java.io.FileNotFoundException;

import android.content.Context;
import android.text.TextUtils;

import com.wl.git.bean.Api;
import com.wl.git.http.AsyncHttpClient;
import com.wl.git.http.AsyncHttpResponseHandler;
import com.wl.git.http.RequestParams;

/**
 * @Description: HttpClient实现类
 * @author gengsong
 * @date 2013-10-15 上午10:53:56
 * @version V1.0
 */
public class SchoolHttpClient {

	public static final String BASE_URL = "http://www.jbxx.org/api/index.php";
	public static final String IMAGE_URL = "http://www.jbxx.org/bbs/attachment/";
	public static final String SEARCH_URL = "http://www.jbxx.org/api/checknewsAction.php";
	private static final String MODULE = "user";

	/**
	 * 登录接口
	 */
	public static final String login_interface = "http://iqueids.hk2227.7eidc.com/api/index.php";
	public static final String USERLOGIN = "userlogin";

	private volatile static SchoolHttpClient instance = null;

	protected static AsyncHttpClient client = new AsyncHttpClient();

	private SchoolHttpClient() {
	}

	public void cancelAll(Context paramContext) {
		if (this.client != null)
			this.client.cancelRequests(paramContext, true);
	}

	public static SchoolHttpClient getInstance() {
		if (instance == null) {
			synchronized (SchoolHttpClient.class) {
				if (instance == null) {
					instance = new SchoolHttpClient();
				}
			}

		}
		return instance;
	}

	public void loginTask(String username, String password, String action,
			String module,
			AsyncHttpResponseHandler paramAsyncHttpResponseHandler) {

		RequestParams localRequestParams = new RequestParams();
		if (!TextUtils.isEmpty(username)) {
			localRequestParams.put("username", username);
		}

		if (!TextUtils.isEmpty(password)) {
			localRequestParams.put("password", password);
		}

		localRequestParams.put("action", action);
		if (!TextUtils.isEmpty(module)) {
			localRequestParams.put("module", module);
		}

		String url = login_interface;
		client.get(url, localRequestParams, paramAsyncHttpResponseHandler);
	}

	/**
	 * 获取个人信息
	 * 
	 * @param handler
	 */
	public static void doHttpPersonalSetting(String userId,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "user");
		params.put("action", "userinfo");
		params.put("userid", userId);
		client.post(BASE_URL, params, handler);
	}
	
	/**
	 * 短消息列表
	 * @param userid
	 * @param action
	 * @param handler
	 */
	public static void doHttpSMSList(String userid,String code,String action,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "msg");
		params.put("action", action);
		params.put("userid", userid);
		params.put("code", code);
		client.get(BASE_URL, params,handler);
	}
	
	/**
	 * 消息的详细界面
	 * @param userid
	 * @param listid
	 * @param action
	 * @param handler
	 */
	public static void doHttpSMSDatail(String userid,String listid,String action,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "msg");
		params.put("action", action);
		params.put("userid", userid);
		params.put("mid", listid);
		client.get(BASE_URL, params,handler);
	}
	
	/**
	 * 发送短信
	 * @param userid
	 * @param touname
	 * @param title
	 * @param contet
	 * @param action
	 * @param handler
	 */
	public static void doHttpSMSSend(String userid,String touname,String title,String contet,String action,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "msg");
		params.put("action", action);
		params.put("userid", userid);
		params.put("touname", touname);
		params.put("title", title);
		params.put("content", contet);
		client.get(BASE_URL, params,handler);
	}
	/**
	 * 删除消息
	 * @param userid
	 * @param action
	 * @param mid
	 * @param handler
	 */
	public static void doHttpSMSDelete(String userid,String action,String mid,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "msg");
		params.put("action", action);
		params.put("userid", userid);
		
		params.put("mid", mid);
		client.get(BASE_URL, params,handler);
	}
	
	
	/**
	 * 修改密码
	 * @param userid
	 * @param oldpwd
	 * @param newpwd
	 * @param handler
	 */
	public  static void doHttpPwd (String userid,String oldpwd,String newpwd,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "user");
		params.put("action", "updatepwd");
		params.put("oldpwd", oldpwd);
		params.put("userid", userid);		
		params.put("newpwd", newpwd);
		client.get(BASE_URL, params,handler);
		
	}
	/**
	 * 
	 * @param userid
	 * @param sex
	 * @param handler
	 * @Description:
	 * @version 1.0
	 * @author wangliu
	 * @created 下午07:39:06
	 */
	public static void doHttpUpdateSex(String userid,String sex,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "user");
		params.put("action", "updatesex");
		params.put("userid", userid);
		params.put("sex", sex);
		client.get(BASE_URL, params,handler);
	}
	
	
	public static void doHttpUpdatePic(String userid,String pic,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		try {
			params.put("pic", new File(pic));
			params.put("module", "user");
			params.put("action", "updatepic");
			params.put("userid", userid);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.post(BASE_URL, params,handler);
	}
	
	
	/**
	 * 获取帖子列表
	 * @author gengsong
	 * @param userid
	 * @param fid
	 */
	public  void doHttpSchoolNoticeList (String userid,String fid,String page,String count,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "bbs");
		params.put("action", "getbbslist");
		params.put("userid", userid);
		params.put("fid", fid);
		params.put("page", page);
		params.put("count", count);
		client.get(BASE_URL, params,handler);
		
	}
	
	/**
	 * 
	* @Title: doHttpPostDetail 
	* @Description: 获取帖子详情
	* @param  userid
	* @param  fid
	* @param  title
	* @param  content
	* @param  handler    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void doHttpPostDetail(String userid,String fid,String tid,String page,String count,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "invitation");
		params.put("action", "getcard");
		params.put("userid", userid);
		params.put("fid", fid);
		params.put("tid", tid);
		params.put("page", page);
		params.put("count", count);
		client.get(BASE_URL, params,handler);
	}
	
	/**
	 * 
	* @Title: doHttpSendPost 
	* @Description: 发布新帖
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	* @author gengsong
	 */
	public void doHttpSendPost(String userid,String fid,String title,String content,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "invitation");
		params.put("action", "newcard");
		params.put("userid", userid);
		params.put("fid", fid);
		params.put("title", title);
		params.put("content", content);
		client.get(BASE_URL, params,handler);
	}
	/**
	 * 
	 * @param view
	 * @Description:添加回复
	 * @version 1.0
	 * @author wangliu
	 * @created 下午04:59:42
	 */
	
	public void doHttpSendReply(String userid,String tid,String fid,String content,
			AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("module", "invitation");
		params.put("action", "setcard");
		params.put("userid", userid);
		params.put("tid", tid);	
		params.put("fid", fid);		
		params.put("content", content);
		client.get(BASE_URL, params,handler);
		
	}
	
	
	
	/**
	 * 
	 * @param keywordString
	 * @param handler
	 * @Description:关键字搜索
	 * @version 1.0
	 * @author wangliu
	 * @created 下午07:31:27
	 */
	
	public void doHttpSearch(String keywordString,AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("keywords", keywordString);
		client.get(SEARCH_URL, params,handler);
		
	}

	public void doHttpTeacherAreaList(String userid,AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "bbs");
		params.put("action", Api.MAIN_API);
		params.put("userid", userid);
		client.get(BASE_URL, params,handler);
	}
	
}
