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
import sg.nus.iss.javaproject.model.ApprovalStatus;
import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.service.LeaveApplicationImplementation;
import sg.nus.iss.javaproject.service.LeaveApplicationInterface;

@Controller
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveApplicationInterface lService;
	
	@Autowired
	public void setLeaveService(LeaveApplicationImplementation lImpl) {
		this.lService=lImpl;
	}
	
	@GetMapping("/apply")
	public String applyLeave(Model model) {
		model.addAttribute("leave",new LeaveApplication());
		return "apply";
	}
	
	@PostMapping("/apply")
	public String submitLeave(@Valid @ModelAttribute("leave")LeaveApplication leaveApplication ,BindingResult bindingResult,HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "apply";
		}
		leaveApplication.setLeaveApprovalStatus(ApprovalStatus.applied);
		return "leaveStatus";
	}
}
