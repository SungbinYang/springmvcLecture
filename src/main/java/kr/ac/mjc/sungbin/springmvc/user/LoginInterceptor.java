package kr.ac.mjc.sungbin.springmvc.user;

import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LogManager.getLogger();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) // 로그인 했을 경우
			return true;

		// 로그인 안했을 경우
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();
		String returnUrl = queryString == null ? requestURI
				: requestURI + "?" + queryString;

		response.sendRedirect(request.getContextPath()
				+ "/app/user/loginForm?returnUrl="
				+ URLEncoder.encode(returnUrl, Charset.defaultCharset()));
		return false;
	}
}