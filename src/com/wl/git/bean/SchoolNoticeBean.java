package com.wl.git.bean;

public class SchoolNoticeBean {

	public String id;
	public String content;//标题
	public String author;//发帖人
	public String createTime;
	public String replay;
	public String replayCount;
	public String popularity; //人气
	public int popularityCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReplay() {
		return replay;
	}
	public void setReplay(String replay) {
		this.replay = replay;
	}
	public String getReplayCount() {
		return replayCount;
	}
	public void setReplayCount(String replayCount) {
		this.replayCount = replayCount;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public int getPopularityCount() {
		return popularityCount;
	}
	public void setPopularityCount(int popularityCount) {
		this.popularityCount = popularityCount;
	}
}
