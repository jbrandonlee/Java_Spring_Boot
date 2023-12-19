package sg.edu.nus.iss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.model.Leave.LeaveStatus;
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
		// Group by Employee (Diaz)
		return "manager-leave-pending";
	}
	
	@GetMapping(value = "/staff")
	public String subordinateList(Model model, HttpSession sessionObj) {
		model.addAttribute("subordinateList",
				employeeService.findAllSubordinates((Employee) sessionObj.getAttribute("employee")));
		return "manager-subordinate-list";
	}
	
	@GetMapping(value = "/staff/{sid}/leave")
	public String subordinateListHistory(@PathVariable(name = "sid") Integer subordinateId, Model model, HttpSession sessionObj) {
		model.addAttribute("subordinateLeaves",
				leaveService.findSubordinateLeaveHistory((Employee) sessionObj.getAttribute("employee"), subordinateId));
		return "subordinate-leave-history";
	}

	@GetMapping(value = "/staff/{sid}/leave/{id}")
	public String subordinateLeaveDetails(@PathVariable(name = "id") Integer leaveId, Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findLeaveById(leaveId);
		model.addAttribute("leave", leave);
		model.addAttribute("subordinateLeaves",
				leaveService.findAllSubordinateLeaveHistoryInDuration((Employee) sessionObj.getAttribute("employee"), leave));
		return "subordinate-leave-details";
	}
	
	@PostMapping(value = "/staff/{sid}/leave/{id}/approve")
	public String approveLeave(@PathVariable(name = "id") Integer leaveId,
							   @Valid @ModelAttribute("leave") Leave leaveForm, BindingResult bindingResult,
							   Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findLeaveById(leaveId);
		leave.setManagerComment(leaveForm.getManagerComment());
		leave.setStatus(LeaveStatus.APPROVED);
		leaveService.updateLeave(leave);
		return "redirect:/manager/overview";
	}
	
	@PostMapping(value = "/staff/{sid}/leave/{id}/reject")
	public String rejectLeave(@PathVariable(name = "id") Integer leaveId,
			   @Valid @ModelAttribute("leave") Leave leaveForm, BindingResult bindingResult,
			   Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findLeaveById(leaveId);
		leave.setManagerComment(leaveForm.getManagerComment());
		leave.setStatus(LeaveStatus.REJECTED);
		leaveService.updateLeave(leave);
		return "redirect:/manager/overview";
	}
}
