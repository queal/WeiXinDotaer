package per.weixin.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientUtils {
	private static final String HTTP_HEADER_CONTENT_ENCODING = "Content-Encoding";
	private static final String ENCODING_GZIP = "gzip";

	public static HttpClient getHttpClient() {
		return new DefaultHttpClient();
	}

	public static String doPost(HttpClient httpClient, String url,
			List<NameValuePair> params) {
		String body = "";
		try {
			HttpPost httppost = new HttpPost(url);
			if (params != null) {
				httppost.setEntity(new UrlEncodedFormEntity(params,
						Consts.UTF_8));
			}
			HttpResponse response = httpClient.execute(httppost);

			InputStream is = null;
			if (isGzipResponse(response)) {
				is = new GZIPInputStream(response.getEntity().getContent());
			} else {
				is = response.getEntity().getContent();
			}

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				IOUtils.copy(is, baos);
				body = new String(baos.toByteArray(), "utf-8");
			}
			httppost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;

	}

	public static String doGet(HttpClient httpClient, String url) {
		String body = "";
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpget);

			InputStream is = null;
			if (isGzipResponse(response)) {
				is = new GZIPInputStream(response.getEntity().getContent());
			} else {
				is = response.getEntity().getContent();
			}

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				IOUtils.copy(is, baos);
				body = new String(baos.toByteArray(), "utf-8");
			}
			httpget.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;
	}

	protected static boolean isGzipResponse(HttpResponse httpResponse) {
		Header encodingHeader = httpResponse
				.getFirstHeader(HTTP_HEADER_CONTENT_ENCODING);
		return (encodingHeader != null && encodingHeader.getValue() != null && encodingHeader
				.getValue().toLowerCase().contains(ENCODING_GZIP));
	}

}
