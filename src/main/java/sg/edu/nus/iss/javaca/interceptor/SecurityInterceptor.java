package sg.edu.nus.iss.javaca.interceptor;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		HttpSession sessionObj = request.getSession();
		
		//username will come from login session
		String username = (String) sessionObj.getAttribute("username");
		if(username == null) {
			response.sendRedirect("/login");
			
			return false;
		}
		return true;
	}
}