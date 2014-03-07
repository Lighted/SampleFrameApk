package com.wl.git.bean;

/*
 * пео╒
 */
public class Sms {

	private String mdate;
	private String title;
	private String username;
	private String mid;
	private String fromuid;

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getFromuid() {
		return fromuid;
	}

	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}

	@Override
	public String toString() {
		return "Sms [mdate=" + mdate + ", title=" + title + ", username="
				+ username + ", mid=" + mid + ", fromuid=" + fromuid + "]";
	}
	

}
