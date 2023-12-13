package sg.nus.iss.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.service.StaffImplementation;
import sg.nus.iss.javaproject.service.StaffInterface;

@Controller
public class StaffContorller {
	@Autowired
	private StaffInterface sService;
	
	@Autowired
	public void setStaffService(StaffImplementation sServiceImpl) {
		this.sService = sServiceImpl;
	}
	
	@RequestMapping("/home")
	public String displayHomePage(Model model) {
		return "homePage";
	}
	
	@RequestMapping("/")
	public String viewLogin(Model model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username")String username,@RequestParam("password")String password,
						HttpSession sessionobj,Model model) {
		Staff staff=sService.getStaffByName(username);
		if(staff!=null&&staff.getPassword().equals(password)) {
			sessionobj.setAttribute("staff",staff);
			return "redirect:/home";
		}
		else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sessionoBj) {
		sessionoBj.invalidate();
		return "redirect:/login";
	}
}
