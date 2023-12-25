package sg.edu.nus.iss.lms.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.lms.exception.UnauthorizedException;
import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Role;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, Object handler) 
							 throws IOException, UnauthorizedException {
		
	    String uri = request.getRequestURI();
	    if (uri.startsWith("/css/") || uri.startsWith("/image/")) {
	      return true;
	    }
	    
	    if (uri.equalsIgnoreCase("/") || uri.equalsIgnoreCase("/home") 
	              || uri.equalsIgnoreCase("/login") || uri.equalsIgnoreCase("/authenticate")) {
	      return true;
	    }
	    
	    HttpSession sessionObj = request.getSession();
		Employee employee = (Employee) sessionObj.getAttribute("employee");		
		if (employee == null) {
			response.sendRedirect("/login");
			return false;
		}
		
		Account account = (Account) sessionObj.getAttribute("account");
	    List<Role> userRoles = account.getRoles();
	    List<String> userRoleNames = new ArrayList<>();
	    for (Role role : userRoles) {
	    	userRoleNames.add(role.getId());
	    }
	    
	    if (uri.startsWith("/admin") && !userRoleNames.contains("admin")) {
	    	throw new UnauthorizedException();
	    }
	    
	    if (uri.startsWith("/manager") && !userRoleNames.contains("manager")) {
	    	throw new UnauthorizedException();
	    }
	    
	    if (uri.startsWith("/staff") && !userRoleNames.contains("staff")) {
	    	throw new UnauthorizedException();
	    }
		
		return true;
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
