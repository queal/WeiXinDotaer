package per.dota.test;

import com.thoughtworks.xstream.XStream;

import per.weixin.pojo.WeiXinMsg;

public class XstreamTest1 {

	public static void main(String[] args) {
		String s = "<xml><ToUserName><![CDATA[gh_768bf9b4b3de]]></ToUserName>"
				+ "<FromUserName><![CDATA[oDwR3jk38D-4AB-V6zXrDN_3coJk]]></FromUserName>"
				+ "<CreateTime>1415346123</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[unsubscribe]]></Event>"
				+ "<EventKey><![CDATA[]]></EventKey>"
				+ "</xml>";
		XStream xStream = new XStream();
		xStream.ignoreUnknownElements();
		xStream.alias("xml", WeiXinMsg.class);
		WeiXinMsg requestMsg = (WeiXinMsg) xStream.fromXML(s);
		String msgType = requestMsg.getMsgType();
		
		System.out.println(msgType);
	}
}
