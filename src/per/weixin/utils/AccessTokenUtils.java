package per.weixin.utils;

public class AccessTokenUtils {

	private static String accessToken;
	private static long expiryDate;

	public static AccessTokenUtils getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		public static AccessTokenUtils INSTANCE = new AccessTokenUtils();
	}

	public static String getAccessToken() {

		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		AccessTokenUtils.accessToken = accessToken;
	}

}
