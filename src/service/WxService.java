package service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import entity.BaseMessage;
import entity.TextMessage;

public class WxService {
	private static final String TOKEN = "abc";

	/*
	 * 验证签名
	 */
	public static boolean check(String timestamp, String nonce, String signature) {

		// 1.将token、timestamp、nonce三个参数按照字典序排序
		String[] strs = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(strs);
		// 2.将三个参数字符串拼接成一个字符串进行sha1加密
		String str = strs[0] + strs[1] + strs[2];
		String mysig = sha1(str);
		System.out.println(mysig);
		System.out.println(signature);
		// 3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信

		return mysig.equalsIgnoreCase(signature);
	}

	// 进行sha1加密
	private static String sha1(String str) {

		try {
			// 获取一个加密对象sha1加密
			MessageDigest md = MessageDigest.getInstance("sha1");
			// 加密
			byte[] digest = md.digest(str.getBytes());
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			StringBuilder sb = new StringBuilder();
			// 处理加密结果
			// 将八位二进制分为高四位与低四位，并分别处理为一位十六进制字符
			for (byte b : digest) {
				sb.append(chars[(b >> 4) & 15]);// 高四位
				sb.append(chars[b & 15]); // 低四位
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 解析xml数据包
	 */
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		try {
			// 读取输入流，获取文档对象
			Document document = reader.read(is);
			// 根据文档对象获取根节点
			Element root = document.getRootElement();
			// 获取根节点的所有的子节点
			List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getStringValue());
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/*
	 * 用于处理所有的事件和消息的回复 返回XML数据包
	 */
	public static String getResponse(Map<String, String> requestMap) {
		String msgType = requestMap.get("MsgType");
		BaseMessage msg = null;
		switch (msgType) {
		
			//处理文本消息
			case "text":
				msg = dealTextMessage(requestMap);
				break;
			case "image":
	
				break;
			case "voice":
	
				break;
			case "video":
	
				break;
			case "shortvideo":
	
				break;
			case "location":
	
				break;
			default:
				break;

		}
		System.out.println(msg);

		return null;
	}

	
	//处理文本消息
	private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
		TextMessage tm = new TextMessage(requestMap, "你好吗？");
		return tm;
	}

}
