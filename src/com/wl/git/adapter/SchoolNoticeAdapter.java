package com.wl.git.adapter;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wl.git.R;
import com.wl.git.bean.SchoolNoticeBean;

/**
 * 
 * @Description: 学校公告adapter
 * @author gengsong
 * @date 2013-10-17 下午6:31:46 
 * @version V1.0
 */
public class SchoolNoticeAdapter extends BaseAdapter {
	private LayoutInflater mInflater = null;
	private LinkedList<SchoolNoticeBean> list = null;
	private Context context;
	
	public SchoolNoticeAdapter(LinkedList<SchoolNoticeBean> linkedList, Context context) {
		list = linkedList;
		this.context = context;
		this.mInflater = LayoutInflater.from(this.context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public SchoolNoticeBean getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.school_notice_list_item, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.notice_content_tv = (TextView) convertView.findViewById(R.id.notice_content_tv);
			holder.hits_count_tv = (TextView) convertView.findViewById(R.id.hits_count_tv);
			holder.replies_count_tv = (TextView) convertView.findViewById(R.id.replies_count_tv);
			holder.auther_tv = (TextView) convertView.findViewById(R.id.auther_tv);
			holder.createtiem_tv = (TextView) convertView.findViewById(R.id.createtiem_tv);
					

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.notice_content_tv.setText(list.get(position).getContent());
		holder.hits_count_tv.setText("人气(" + list.get(position).getPopularity() + ")");
		holder.replies_count_tv.setText("回复(" + list.get(position).getReplayCount() + ")");
		holder.auther_tv.setText("作者：" + list.get(position).getAuthor());
		holder.createtiem_tv.setText("[" + list.get(position).getCreateTime() + "]");

		return convertView;
	}

	private final class ViewHolder {
		TextView notice_content_tv;
		TextView hits_count_tv;//人气
		TextView replies_count_tv;//回帖
		TextView auther_tv;//作者
		TextView createtiem_tv;//time
		
	}
}
