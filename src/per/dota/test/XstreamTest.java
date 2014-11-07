package per.dota.test;

import per.weixin.pojo.WeiXinMsg;

import com.thoughtworks.xstream.XStream;

public class XstreamTest {

	public static void main(String[] args) {
		WeiXinMsg msg = new WeiXinMsg();

		msg.setCreateTime(System.currentTimeMillis());
		msg.setMsgId("1");
		msg.setMsgType("2");
		msg.setToUserName("123");
		msg.setFromUserName("321");

		XStream xstream = new XStream();
		xstream.alias("xml", WeiXinMsg.class);

		String s = xstream.toXML(msg);
		msg = (WeiXinMsg) xstream.fromXML(s);

		System.out.println(msg);

	}
}
