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
	 * ��֤ǩ��
	 */
	public static boolean check(String timestamp, String nonce, String signature) {

		// 1.��token��timestamp��nonce�������������ֵ�������
		String[] strs = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(strs);
		// 2.�����������ַ���ƴ�ӳ�һ���ַ�������sha1����
		String str = strs[0] + strs[1] + strs[2];
		String mysig = sha1(str);
		System.out.println(mysig);
		System.out.println(signature);
		// 3.�����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��

		return mysig.equalsIgnoreCase(signature);
	}

	// ����sha1����
	private static String sha1(String str) {

		try {
			// ��ȡһ�����ܶ���sha1����
			MessageDigest md = MessageDigest.getInstance("sha1");
			// ����
			byte[] digest = md.digest(str.getBytes());
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			StringBuilder sb = new StringBuilder();
			// ������ܽ��
			// ����λ�����Ʒ�Ϊ����λ�����λ�����ֱ���Ϊһλʮ�������ַ�
			for (byte b : digest) {
				sb.append(chars[(b >> 4) & 15]);// ����λ
				sb.append(chars[b & 15]); // ����λ
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * ����xml���ݰ�
	 */
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		try {
			// ��ȡ����������ȡ�ĵ�����
			Document document = reader.read(is);
			// �����ĵ������ȡ���ڵ�
			Element root = document.getRootElement();
			// ��ȡ���ڵ�����е��ӽڵ�
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
	 * ���ڴ������е��¼�����Ϣ�Ļظ� ����XML���ݰ�
	 */
	public static String getResponse(Map<String, String> requestMap) {
		String msgType = requestMap.get("MsgType");
		BaseMessage msg = null;
		switch (msgType) {
		
			//�����ı���Ϣ
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

	
	//�����ı���Ϣ
	private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
		TextMessage tm = new TextMessage(requestMap, "�����");
		return tm;
	}

}
