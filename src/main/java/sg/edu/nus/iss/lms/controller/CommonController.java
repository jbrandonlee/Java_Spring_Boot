package sg.edu.nus.iss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Staff;
import sg.edu.nus.iss.lms.service.StaffService;
import sg.edu.nus.iss.lms.service.StaffServiceImpl;

@Controller
public class CommonController {
	
	@Autowired
	private StaffService sService;
	
	@Autowired
	public void setStaffService(StaffServiceImpl sServiceImpl) {
		this.sService = sServiceImpl;
	}
	
	@RequestMapping("/home")
	public String displayHomePage(Model model) {
		return "homePage";
	}
	
	@RequestMapping("/")
	public String viewLogin(Model model) {
		model.addAttribute("account",new Account());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("account") Account account,
						BindingResult bindingResult,HttpSession sessionobj,Model model) {
		if(bindingResult.hasErrors()) {
			return "login";
		}
		else {
			String username=account.getUsername();
			String password=account.getPassword();
			Staff staff=sService.getStaffByUsername(username); 
			if(staff!=null) {
				String accountPassword=staff.getAccount().getPassword();
				if(accountPassword.equals(password)) {
					sessionobj.setAttribute("staff",staff);
					return "redirect:/manager/home";
				}
				else {
					model.addAttribute("loginMessage", "Incorrect username/password");
				    return "login";
				}
			}
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sessionoBj) {
		sessionoBj.invalidate();
		return "redirect:/";
	}

}
