package jp.co.calace.redwing.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthHandler implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("auth handler accessed uri:"+request.getContextPath());
		
		LoginModel userData = (LoginModel) request.getSession().getAttribute("userInfo");
		
		// check if user is loggedin
		if (userData == null) {
			logger.info("already logged out");
			
			response.sendRedirect(request.getContextPath()+"/room/login");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
