package per.dota.pojo;

public class DotaVideoPojo {
	private String title;
	private String brief;
	private String picurl;
	private String url;
	private String author;
	private String time;

	public DotaVideoPojo(String title, String brief, String picurl, String url,
			String author, String time) {
		super();
		this.title = title;
		this.brief = brief;
		this.picurl = picurl;
		this.url = url;
		this.author = author;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
