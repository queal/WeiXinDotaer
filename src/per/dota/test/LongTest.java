package per.dota.test;

import java.util.ArrayList;
import java.util.List;

import per.dota.pojo.DotaVideoPojo;
import per.dota.utils.Dota178SpiderUtils;
import per.weixin.pojo.Articles;
import per.weixin.pojo.Item;
import per.weixin.pojo.WeiXinPicContentMsg;

import com.thoughtworks.xstream.XStream;

public class LongTest {

	public static void main(String[] args) {
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

		XStream xStream = new XStream();
		xStream.alias("xml", WeiXinPicContentMsg.class);
		xStream.alias("Articles", Articles.class);
		xStream.alias("item", Item.class);
		xStream.addImplicitCollection(Articles.class, "item");

		String responseMsgStr = xStream.toXML(responseMsg);
		System.out.println(responseMsgStr);
	}
}
