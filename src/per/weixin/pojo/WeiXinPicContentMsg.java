package per.weixin.pojo;

public class WeiXinPicContentMsg extends WeiXinMsg {
	private String ArticleCount;
	private Articles Articles;

	public WeiXinPicContentMsg() {
		this.MsgType = "news";
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public Articles getArticles() {
		return Articles;
	}

	public void setArticles(Articles articles) {
		Articles = articles;
	}

	@Override
	public String toString() {
		return "WeiXinPicContentMsg [ArticleCount=" + ArticleCount
				+ ", Articles=" + Articles + ", ToUserName=" + ToUserName
				+ ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", MsgId=" + MsgId
				+ "]";
	}

}
