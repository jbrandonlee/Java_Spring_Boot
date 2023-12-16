package sg.nus.iss.javaproject.controller;

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
import sg.nus.iss.javaproject.model.Account;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.service.StaffImplementation;
import sg.nus.iss.javaproject.service.StaffInterface;

@Controller
public class StaffController {
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
					return "redirect:/home";
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
