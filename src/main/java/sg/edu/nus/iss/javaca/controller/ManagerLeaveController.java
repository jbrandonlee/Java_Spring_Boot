package sg.edu.nus.iss.javaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.javaca.model.Leave;
import sg.edu.nus.iss.javaca.model.StatusEnum;
import sg.edu.nus.iss.javaca.service.LeaveService;

@Controller
@RequestMapping("/manager")
public class ManagerLeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@GetMapping("/pending")
	public String listLeaves(HttpSession session, Model model) {
		
		String managerId = (String) session.getAttribute("managerId");
		
		List<Leave> pendingLeaves = leaveService.findPendingLeaves(managerId, StatusEnum.Applied);
		
		model.addAttribute("pendingLeaves", pendingLeaves);
		
		return "leave-list";
	}

}
