package per.dota.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import per.dota.utils.Contants;

public class AuthTest {

	public static void main(String[] args) throws Exception {
		String signature = "851a6c4e68b9b6a568c45d9cce33abb0d40f0b95";
		String timestamp = "1414650637";
		String nonce = "1554508072";
		String echostr = "394424428206620589";

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

		System.out.println(calSignature.equals(signature));
		System.out.println("calSignature: " + calSignature);
		System.out.println("signature: " + signature);

	}
}
