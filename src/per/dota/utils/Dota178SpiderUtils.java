package per.dota.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import per.dota.pojo.DotaVideoPojo;
import per.weixin.utils.HttpClientUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Dota178SpiderUtils {

	private static final String spiderUrl = "http://dota.178.com/public/atcount/dota_v.js";

	public static List<DotaVideoPojo> getVideo(int count) {
		List<DotaVideoPojo> lists = null;

		try {
			lists = new ArrayList<DotaVideoPojo>();
			HttpClient httpClient = HttpClientUtils.getHttpClient();
			String retBody = HttpClientUtils.doGet(httpClient, spiderUrl);

			retBody = retBody.substring("f_dota_v(".length(),
					retBody.lastIndexOf(")"));

			JSONObject jsonObject = JSON.parseObject(retBody);

			JSONArray dotaVideos = jsonObject.getJSONArray("day");
			for (int i = 0; i < dotaVideos.size() && i < count; i++) {
				JSONObject video = dotaVideos.getJSONObject(i);
				String title = video.getString("title");
				String brief = video.getString("brief");
				String picurl = video.getString("picurl");
				String url = video.getString("url");
				String author = video.getString("time");
				String time = video.getString("author");

				DotaVideoPojo dotaVideoPojo = new DotaVideoPojo(title, brief,
						picurl, url, author, time);
				lists.add(dotaVideoPojo);
			}
		} catch (Exception e) {
			lists = null;
			e.printStackTrace();
		}

		return lists;
	}
}
