package com.wl.git.bean;
/**
 * 
 * @Description: ImageAndUrl
 * @author gengsong
 * @date 2013-10-16 下午4:10:58 
 * @version V1.0
 */
public class ImageAndUrl {
	/**
	 * 图片自己的路径
	 */
	private String imageurl;
	/**
	 * 点击图片要跳转的连接
	 */
	private String url;

	public ImageAndUrl(String imageurl, String url) {
		super();
		this.imageurl = imageurl;
		this.url = url;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ImageAndUrl() {
		super();
	}

	@Override
	public String toString() {
		return "ImageAndUrl [imageurl=" + imageurl + ", url=" + url + "]";
	}
}
