package per.weixin.pojo;

public class WeiXinBasicMsg extends WeiXinMsg {

	private String Content;

	public WeiXinBasicMsg() {
		this.MsgType = "text";
	}
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toString() {
		return "WeiXinBasicMsg [Content=" + Content + ", ToUserName="
				+ ToUserName + ", FromUserName=" + FromUserName
				+ ", CreateTime=" + CreateTime + ", MsgType=" + MsgType
				+ ", MsgId=" + MsgId + "]";
	}



}
