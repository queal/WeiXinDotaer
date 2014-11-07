package per.dota.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import per.dota.pojo.DotaVideoPojo;
import per.dota.utils.Contants;
import per.dota.utils.Dota178SpiderUtils;
import per.weixin.pojo.Articles;
import per.weixin.pojo.Item;
import per.weixin.pojo.WeiXinBasicMsg;
import per.weixin.pojo.WeiXinEventMsg;
import per.weixin.pojo.WeiXinMsg;
import per.weixin.pojo.WeiXinPicContentMsg;
import per.weixin.pojo.builder.WeiXinMsgBuilder;

import com.thoughtworks.xstream.XStream;

/**
 * Servlet implementation class MsgServlet
 */
@WebServlet("/MsgServlet")
public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MsgServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			String echostr = request.getParameter("echostr");
			if (echostr != null) {
				if (checkSignature(request, response)) {
					response.getOutputStream().write(echostr.getBytes());
					System.out.println("认证微信服务器成功!");
				} else {
					System.out.println("认证微信服务器失败!");
				}
				return;
			}

			if (checkSignature(request, response)) {
				XStream xStream = new XStream();
				xStream.ignoreUnknownElements();
				xStream.alias("xml", WeiXinMsg.class);

				String body = IOUtils.toString(request.getInputStream(),
						"utf-8");
				
				WeiXinMsg requestMsg = (WeiXinMsg) xStream.fromXML(body);
				String msgType = requestMsg.getMsgType();

				if (msgType.equals(per.weixin.utils.Contants.MSG_TYPE_TEXT)) {
					processTextMsg(body, response);
				} else if (msgType
						.equals(per.weixin.utils.Contants.MSG_TYPE_EVENT)) {
					processEventMsg(body, response);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkSignature(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		List<String> lists = new ArrayList<String>();
		lists.add(Contants.WEIXIN_TOKEN);
		lists.add(timestamp);
		lists.add(nonce);
		Collections.sort(lists);

		StringBuilder sb = new StringBuilder();
		for (String s : lists) {
			sb.append(s);
		}
		String calSignature = new String(DigestUtils.sha1Hex(sb.toString()));

		if (signature.equals(calSignature)) {
			return true;
		} else {
			return false;
		}
	}

	private void processTextMsg(String body, HttpServletResponse response)
			throws Exception {
		XStream xStream = new XStream();
		xStream.alias("xml", WeiXinBasicMsg.class);
		WeiXinBasicMsg requestMsg = (WeiXinBasicMsg) xStream.fromXML(body);

		WeiXinPicContentMsg responseMsg = new WeiXinPicContentMsg();
		List<DotaVideoPojo> lists = Dota178SpiderUtils.getVideo(3);
		responseMsg.setArticleCount(lists.size() + "");

		Articles articles = new Articles();
		List<Item> items = new ArrayList<Item>();
		for (DotaVideoPojo video : lists) {
			Item item = new Item();
			item.setTitle(video.getTitle());
			item.setDescription(video.getBrief());
			item.setUrl(video.getUrl());
			item.setPicUrl(video.getPicurl());
			items.add(item);
		}
		articles.setItem(items);
		responseMsg.setArticles(articles);

		responseMsg.setMsgType("news");
		responseMsg.setCreateTime(System.currentTimeMillis());
		responseMsg.setToUserName(requestMsg.getFromUserName());
		responseMsg.setFromUserName(requestMsg.getToUserName());

		xStream = new XStream();
		xStream.alias("xml", WeiXinPicContentMsg.class);
		xStream.alias("Articles", Articles.class);
		xStream.alias("item", Item.class);
		xStream.addImplicitCollection(Articles.class, "item");

		String responseMsgStr = xStream.toXML(responseMsg);
		response.getOutputStream().write(responseMsgStr.getBytes("utf-8"));
	}

	private void processEventMsg(String body, HttpServletResponse response)
			throws Exception {
		XStream xStream = new XStream();
		xStream.alias("xml", WeiXinEventMsg.class);
		WeiXinEventMsg requestMsg = (WeiXinEventMsg) xStream.fromXML(body);

		if (requestMsg.getEvent().equals("subscribe")) {
			WeiXinBasicMsg responseMsg = WeiXinMsgBuilder.buildWeiXinBasicMsg(
					requestMsg.getToUserName(), requestMsg.getFromUserName(),
					"欢迎订阅dotaer,回复任意文字将直接获取最新dota1视频!");
			xStream = new XStream();
			xStream.alias("xml", WeiXinBasicMsg.class);
			String responseMsgStr = xStream.toXML(responseMsg);
			response.getOutputStream().write(responseMsgStr.getBytes("utf-8"));
		}

	}
}
