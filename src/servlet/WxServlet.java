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
		// 校验请求
		if (WxService.check(timestamp, nonce, signature)) {
			PrintWriter out = response.getWriter();
			// 原样返回echostr参数
			out.print(echostr);
			System.out.println("接入成功");
			out.flush();
			out.close();
		} else {
			System.out.println("接入失败");
		}

	}

	// 接收消息和事件推送
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //设置编码方式
		response.setCharacterEncoding("utf-8");
		// 处理时间和事件推送
		Map<String, String> requestMap = WxService.parseRequest(request
				.getInputStream());
		System.out.println(requestMap);
		// 准备回复的数据包
		//字符串中不能包含空格
		String respXml = WxService.getResponse(requestMap);
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.flush();
		out.close();
		
		
	}
}
