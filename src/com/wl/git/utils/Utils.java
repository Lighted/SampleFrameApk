package com.wl.git.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;

/**
 * 
 * @Description: ������
 * @author gengsong
 * @date 2013-10-15 ����12:40:19 
 * @version V1.0
 */
public class Utils {

	
	/*
	 * �����ж�0���������磬1�����ֻ�����,2����wifi��3��������������
	 */
	public static int network_Identification(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].isConnected()) {
						if (0 == info[i].getType()) {
							return 1;
						}
						if (1 == info[i].getType()) {
							return 2;
						}
						return 3;
					}

				}
			}
		}
		return 0;
	}
	
	/**
	 * �����������
	 * 
	 * @return
	 */
	public static boolean checkConnection(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			return networkInfo.isAvailable();
		}
		return false;
	}
	
	/**
	 * Wifi�Ƿ����
	 * @param mContext
	 * @return
	 */
	public static boolean isWifi(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.getTypeName().equals("WIFI")) {
			return true;
		}
		return false;
	}
	
	/*
	 * ��֤�ǳ��Ƿ�Ϊ������
	 */
	public static boolean isDigit(String str) {
		if (null == str) {
			return false;
		}
		boolean isDigit = true;
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				isDigit = false;
				break;
			}
		}
		return isDigit;
	}
	
	/**
	 * Drawableת����Bitmap
	 * @param d
	 * @return
	 */
	public static Bitmap drawToBmp(Drawable d) {
		if (null != d) {
			BitmapDrawable bd = (BitmapDrawable) d;
			return bd.getBitmap();
		}
		return null;
	}
	
	/*
	 * �ӷ������˻�ȡ����
	 */
	public static String getData(String baseURL,
			List<NameValuePair> requestParams) {
		if (null != requestParams) {
			HttpPost httpPost = new HttpPost(baseURL);
			String responseResult = null;
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(requestParams,
						HTTP.UTF_8));
				HttpClient httpClient = new DefaultHttpClient();
				httpClient.getParams().setParameter(
						CoreConnectionPNames.CONNECTION_TIMEOUT, 100 * 1000);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					responseResult = EntityUtils.toString(httpResponse
							.getEntity());
				} else {
					return null;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				httpPost.abort();
			}
			if (!TextUtils.isEmpty(responseResult))
				return responseResult;
		}
		return null;
	}
	
	public static String getBaseURL() {
		return SchoolHttpClient.BASE_URL;
	}
	
	 /**
	  * @deprecated �˳�����
  	  * @author gengsong
  	  * @param context
  	  **/
  	public static void showExitDialog(final Context context) {
  		  AlertDialog.Builder builder = new Builder(context);
  		  builder.setMessage("ȷ���˳���");
  		  builder.setTitle("��ʾ");
  		  builder.setPositiveButton(R.string.ok, new OnClickListener() {

  			@Override
  			public void onClick(DialogInterface dialog, int which) {
  				dialog.dismiss();
  				ActivityManager.getInstance().exit();
  			}
  		});

  		builder.setNegativeButton(R.string.cancel, new OnClickListener() {

  			@Override
  			public void onClick(DialogInterface dialog, int which) {
  				dialog.dismiss();
  			}
  		});

  		builder.create().show();
  	}
  	
  	/**
  	 * @Title: loadImageFromNetwork 
  	 * @Description: ��������ͼƬ
  	 * @param @param url
  	 * @param @return    �趨�ļ� 
  	 * @return Bitmap    �������� 
  	 * @throws
  	 * @author gengsong
  	 */
  	public static Bitmap loadImageFromNetwork(String url){
	      try {
	          Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
	          return bitmap;
	      } catch (Exception e) {
	          e.printStackTrace();
	    }
		return null;
	 }
  	
  	
  	public static String fomatDate(String milliseconds){
  		SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd");
  		Date date = new Date(Long.parseLong(milliseconds));
		return dateformater.format(date);
  		
  	}
  	
  	/**
	 * @author gengsong
	 * @discription ��ȡ���ر���String����
	 * */
	public static String readStringData(Context context, String key) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String data = sharedPreferences.getString(key, null);
		return data;
	}

	public static String readData(Context context, String key, int type) {
		SharedPreferences eHuiSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String b = eHuiSharedPreferences.getString(key, null);
		return b;
	}
	
	public static int readIntData(Context context, String key, int type) {
		SharedPreferences eHuiSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		int intData = eHuiSharedPreferences.getInt(key, type);
		return intData;
	}
	
	public static Boolean readBooleanData(Context context, String key, boolean defaulValue) {
		SharedPreferences eHuiSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Boolean flag = eHuiSharedPreferences.getBoolean(key, defaulValue);
		return flag;
	}

	
	/**
	 * @author gengsong
	 * �򱾵ر���String����
	 * */
	public static void writeStringData(Context context, String key, String value) {
		SharedPreferences eHuiSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = eHuiSharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	/**
	 * ��app��д��boolean����
	 */
	public static void writeBooleanData(Context context, String key, boolean value) {
		SharedPreferences eHuiSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = eHuiSharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * ��app��д����������
	 * */
	public static void writeIntData(Context context, String key, int value) {
		SharedPreferences sSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sSharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	/**
	 * ����Ƿ����SDCard
	 * @return
	 */
	public static boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
     * @description ��һ���ļ�ת��Ϊ�ֽ�
     * @param file
     * @author wangliu
     * @return  string
     * @throws Exception
     */
    public static String getString(File file) throws Exception
    {
        byte[] bytes = null;
        if(file!=null)
        {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if(length>Integer.MAX_VALUE)   //���ļ��ĳ��ȳ�����int�����ֵ
            {
                System.out.println("this file is max ");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while(offset<bytes.length&&(numRead=is.read(bytes,offset,bytes.length-offset))>=0)
            {
                offset+=numRead;
            }
            //����õ����ֽڳ��Ⱥ�fileʵ�ʵĳ��Ȳ�һ�¾Ϳ��ܳ�����
            if(offset<bytes.length)
            {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
      
        
        
        String uploadBuffer=new String(Base64.encode(bytes,0,bytes.length,Base64.DEFAULT));
        return uploadBuffer;
    }
    
    
    
    public static boolean  saveBitmap2file(Bitmap bmp,String filename){  
        CompressFormat format= Bitmap.CompressFormat.JPEG;  
       int quality = 100;  
       OutputStream stream = null;  
       try {  
               stream = new FileOutputStream("/sdcard/" + filename);  
       } catch (FileNotFoundException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
       }  
 
       return bmp.compress(format, quality, stream);  
       }
    
  
	
    
}
