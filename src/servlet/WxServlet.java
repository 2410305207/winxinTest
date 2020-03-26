package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.WxService;

@WebServlet("/wx")
public class WxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Get");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		// У������
		if (WxService.check(timestamp, nonce, signature)) {
			PrintWriter out = response.getWriter();
			// ԭ������echostr����
			out.print(echostr);
			System.out.println("����ɹ�");
			out.flush();
			out.close();
		} else {
			System.out.println("����ʧ��");
		}

	}

	// ������Ϣ���¼�����
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //���ñ��뷽ʽ
		response.setCharacterEncoding("utf-8");
		// ����ʱ����¼�����
		Map<String, String> requestMap = WxService.parseRequest(request
				.getInputStream());
		System.out.println(requestMap);
		// ׼���ظ������ݰ�
		//�ַ����в��ܰ����ո�
		String respXml = WxService.getResponse(requestMap);
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.flush();
		out.close();
		
		
	}
}
