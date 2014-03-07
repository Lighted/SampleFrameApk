package com.wl.git.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wl.git.bean.Sms;

/**
 * Json½âÎö
 * @author Administrator
 *
 */
public class JsonUtils {

	public static List<Sms> getSmsList(String str, Class<Sms> cls) {
		List<Sms> sms = new ArrayList<Sms>();
		try {
			Gson  gson = new Gson();
			TypeToken<List<Sms>> list = new TypeToken<List<Sms>>() {};
			sms = gson.fromJson(str, list.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sms;
	}
}
