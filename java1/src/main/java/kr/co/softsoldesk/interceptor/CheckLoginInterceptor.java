package kr.co.softsoldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	//로그인 여부 판단하는 UserBean
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	} //매개변수로 가져온 로그인유저빈을 주입
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(loginUserBean.isUserLogin()==false) {//로그아웃 상태
			//루트경로 가져오기
			String contextPath = request.getContextPath(); 
			response.sendRedirect(contextPath + "/user/not_login");
			return false;
		}
		
		return true; //로그인상태
	}
	
	
	

	
	
	
}
