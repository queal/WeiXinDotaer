package per.weixin.pojo.builder;

import per.weixin.pojo.WeiXinBasicMsg;
import per.weixin.utils.Contants;

public class WeiXinMsgBuilder {

	public static WeiXinBasicMsg buildWeiXinBasicMsg(String fromUserName, String toUserName,
			String content) {
		WeiXinBasicMsg msg = new WeiXinBasicMsg();
		msg.setContent(content);
		msg.setCreateTime(System.currentTimeMillis());
		msg.setFromUserName(fromUserName);
		msg.setToUserName(toUserName);
		msg.setMsgType(Contants.MSG_TYPE_TEXT);
		return msg;
	}

}
