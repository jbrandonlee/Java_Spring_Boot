package sg.nus.iss.javaproject.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sg.nus.iss.javaproject.model.Staff;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, Object handler) 
							 throws IOException {
		HttpSession sessionOBJ=request.getSession();
		Staff staff=(Staff)sessionOBJ.getAttribute("staff");
		if(staff!=null) {
			return true;
		}
		else {
			response.sendRedirect("/");
			return false;
		}
	}
	 
	@Override
	public void postHandle(HttpServletRequest request, 
	HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	}
	 
	@Override
	public void afterCompletion(HttpServletRequest request, 
	HttpServletResponse response, Object handler, Exception ex) {
	}
}
