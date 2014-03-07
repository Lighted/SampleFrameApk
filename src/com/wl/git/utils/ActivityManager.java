package com.wl.git.utils;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
/**
 * 
 * ���ڴ����˳�����ʱ�����˳����е�activity,ͨ����
 * @author gengsong
 * @date 2013-11-5
 *
 */
public class ActivityManager {

	private List<Activity> activityList = new LinkedList<Activity>();
	private static ActivityManager instance;

	private ActivityManager() {
	}

	// ����ģʽ�л�ȡΨһ��MyApplicationʵ��
	public static ActivityManager getInstance() {
		if (null == instance) {
			instance = new ActivityManager();
		}
		return instance;
	}

	// ���Activity��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// ��������Activity��finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}