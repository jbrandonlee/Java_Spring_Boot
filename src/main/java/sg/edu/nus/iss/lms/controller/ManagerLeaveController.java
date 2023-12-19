package sg.edu.nus.iss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.service.EmployeeService;
import sg.edu.nus.iss.lms.service.LeaveService;
import sg.edu.nus.iss.lms.service.OvertimeService;

@Controller
@RequestMapping(value = "/manager")
public class ManagerLeaveController {
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	LeaveService leaveService;
	
	@Autowired
	OvertimeService overtimeService;
	
	@GetMapping(value = {"/", "/overview"})
	public String managerHome(Model model, HttpSession sessionObj) {
		model.addAttribute("leavePending",
				leaveService.findAllSubordinatePendingLeaves((Employee) sessionObj.getAttribute("employee")));
		model.addAttribute("overtimePending",
				overtimeService.findAllSubordinatePendingOvertimes((Employee) sessionObj.getAttribute("employee")));
		return "manager-overview";
	}
	
	@GetMapping(value = "/leave/pending")
	public String pendingLeaves() {
		// Group by Employee
		return "manager-leave-pending";
	}
	
	@GetMapping(value = "/staff")
	public String subordinateList(Model model, HttpSession sessionObj) {
		model.addAttribute("subordinateList",
				employeeService.findAllSubordinates((Employee) sessionObj.getAttribute("employee")));
		return "manager-subordinate-list";
	}
	
	@GetMapping(value = "/staff/{sid}/leave")
	public String subordinateListHistory() {
		return "subordinate-leave-history";
	}

	@GetMapping(value = "/staff/{sid}/leave/{id}")
	public String subordinateLeaveDetails() {
		return "subordinate-leave-details";
	}
	
	@PostMapping(value = "/staff/{sid}/leave/{id}/approve")
	public String approveLeave() {
		return "redirect:/manager/leave/pending";
	}
	
	@PostMapping(value = "/staff/{sid}/leave/{id}/reject")
	public String rejectLeave() {
		return "redirect:/manager/leave/pending";
	}

}
