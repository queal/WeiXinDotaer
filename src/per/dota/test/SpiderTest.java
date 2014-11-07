package per.dota.test;

import org.apache.http.client.HttpClient;

import per.weixin.utils.HttpClientUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SpiderTest {

	public static void main(String[] args) {
		String spiderUrl = "http://dota.178.com/public/atcount/dota_v.js";
		HttpClient httpClient = HttpClientUtils.getHttpClient();
		String retBody = HttpClientUtils.doGet(httpClient, spiderUrl);

		retBody = retBody.substring("f_dota_v(".length(),
				retBody.lastIndexOf(")"));

		JSONObject jsonObject = JSON.parseObject(retBody);

		JSONArray dotaVideos = jsonObject.getJSONArray("day");
		for (int i = 0; i < dotaVideos.size() && i < 3; i++) {
			JSONObject video = dotaVideos.getJSONObject(i);
			String title = video.getString("title");
			String brief = video.getString("brief");
			String picurl = video.getString("picurl");
			String url = video.getString("url");

			System.out.println(title);
			System.out.println(brief);
			System.out.println(picurl);
			System.out.println(url);
		}

	}
}
