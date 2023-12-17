package sg.edu.nus.iss.lms.controller;

import java.util.List;

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
import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.service.LeaveEntitlementService;
import sg.edu.nus.iss.lms.service.LeaveService;
import sg.edu.nus.iss.lms.service.LeaveTypeService;


@Controller
@RequestMapping(value = "/staff")
public class StaffLeaveController {
	
	@Autowired
	LeaveService leaveService;
	
	@Autowired
	LeaveTypeService leaveTypeService;
	
	@Autowired
	LeaveEntitlementService leaveEntitlementService;

	@GetMapping(value = { "/leave/overview"})
	public String staffHome(Model model, HttpSession sessionObj) {
		model.addAttribute("leaveEntitlement", leaveEntitlementService.findAllLeaveEntitlementByEmployee((Employee) sessionObj.getAttribute("employee")));
		model.addAttribute("leaveUpcoming", leaveService.findEmployeeLeavesUpcoming((Employee) sessionObj.getAttribute("employee")));
		return "leave-overview";
	}
	
	// CREATE
	@GetMapping(value = { "/leave/apply"})
	public String staffLeaveApplicationForm(Model model, HttpSession sessionObj) {
		model.addAttribute("leaveTypes", leaveTypeService.getTypeNames());
		model.addAttribute("leaveEntitlement", leaveEntitlementService.findAllLeaveEntitlementByEmployee((Employee) sessionObj.getAttribute("employee")));
		return "leave-apply";
	}
	
	@PostMapping(value = { "/leave/apply"})
	public String staffApplyLeave(@Valid @ModelAttribute("leave") Leave leaveForm, BindingResult bindingResult, Model model, HttpSession sessionObj) {
		// Add Backend Validation + Display Thymeleaf Errors
		LeaveType currLeaveType = leaveTypeService.findByType(leaveForm.getLeaveTypeString());
		leaveForm.setLeaveType(currLeaveType);
		leaveForm.setEmployee((Employee) sessionObj.getAttribute("employee"));
		leaveForm.setStatus(LeaveStatus.APPLIED);
		leaveService.createLeave(leaveForm);
		
		return "redirect:/staff/leave/overview";
	}
	
	// RETRIEVE LIST
	// SHOW CURR YEAR, NOT 'LeaveStatus.DELETED' OR 'LeaveStatus.CANCELLED'
	@GetMapping(value = { "/leave/history"})
	public String staffLeaveHistory(Model model, HttpSession sessionObj) {
		List<Leave> leaveHistory = leaveService.findEmployeeLeavesCurrYear((Employee) sessionObj.getAttribute("employee"));
		model.addAttribute("leaveHistory", leaveHistory);
		model.addAttribute("showAll", false);
		return "leave-history";
	}
	
	// SHOW ALL
	@GetMapping(value = { "/leave/history/all"})
	public String staffLeaveHistoryAll(Model model, HttpSession sessionObj) {
		List<Leave> leaveHistory = leaveService.findAllEmployeeLeaves((Employee) sessionObj.getAttribute("employee"));
		model.addAttribute("leaveHistory", leaveHistory);
		model.addAttribute("showAll", true);
		return "leave-history";
	}
	
	// RETRIEVE ONE
	@GetMapping(value = { "/leave/details/{id}"})
	public String staffLeaveDetail(@PathVariable(name="id") Integer leaveId, Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findEmployeeLeaveId((Employee) sessionObj.getAttribute("employee"), leaveId);
		model.addAttribute("leave", leave);
		return "leave-details";
	}

	// UPDATE
	// Update LeaveStatus -> UPDATED or DELETED or CANCELLED (if APPROVED)
	// Show LeaveStatus at Top
	@GetMapping(value = { "/leave/edit/{id}"})
	public String staffLeaveEditForm(@PathVariable(name="id") Integer leaveId, Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findEmployeeLeaveId((Employee) sessionObj.getAttribute("employee"), leaveId);
		model.addAttribute("leave", leave);
		model.addAttribute("leaveTypes", leaveTypeService.getTypeNames());
		model.addAttribute("leaveEntitlement", leaveEntitlementService.findAllLeaveEntitlementByEmployee((Employee) sessionObj.getAttribute("employee")));
		return "leave-edit";
	}
	
	@PostMapping(value = { "/leave/edit/{id}"})
	public String staffEditLeave(@PathVariable(name="id") Integer leaveId, @Valid @ModelAttribute("leave") Leave leaveForm, BindingResult bindingResult, Model model, HttpSession sessionObj) {
		// Add Backend Validation + Display Thymeleaf Errors
		leaveForm.setId(leaveId);
		LeaveType currLeaveType = leaveTypeService.findByType(leaveForm.getLeaveTypeString());
		leaveForm.setLeaveType(currLeaveType);
		leaveForm.setEmployee((Employee) sessionObj.getAttribute("employee"));
		leaveForm.setStatus(LeaveStatus.UPDATED);
		leaveService.updateLeave(leaveForm);
		
		return "redirect:/staff/leave/overview";
	}
	
	// CANCEL
	@PostMapping(value = { "/leave/cancel/{id}"})
	public String staffCancelLeave(@PathVariable(name="id") Integer leaveId, Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findEmployeeLeaveId((Employee) sessionObj.getAttribute("employee"), leaveId);
		
		// If Leave does not belong to Employee, or Leave does not exist
		if (leave == null) {
			model.addAttribute("errorMsg", "Invalid Action.");
			return "redirect:/staff/details/" + leaveId;
		}
		
		// If CANCEL is not allowed
		if (leave.getStatus() != LeaveStatus.APPROVED) {
			model.addAttribute("errorMsg", "Invalid Action.");
			return "redirect:/staff/details/" + leaveId;
		}
		
		leave.setStatus(LeaveStatus.CANCELLED);
		leaveService.updateLeave(leave);
		return "redirect:/staff/leave/overview";
	}
	
	// DELETE
	@PostMapping(value = { "/leave/delete/{id}"})
	public String staffDeleteLeave(@PathVariable(name="id") Integer leaveId, Model model, HttpSession sessionObj) {
		Leave leave = leaveService.findEmployeeLeaveId((Employee) sessionObj.getAttribute("employee"), leaveId);
		
		// If Leave does not belong to Employee, or Leave does not exist
		if (leave == null) {
			model.addAttribute("errorMsg", "Invalid Action.");
			return "redirect:/staff/details/" + leaveId;
		}
		
		// If DELETE is not allowed
		if (leave.getStatus() != LeaveStatus.APPLIED || leave.getStatus() != LeaveStatus.UPDATED) {
			model.addAttribute("errorMsg", "Invalid Action.");
			return "redirect:/staff/details/" + leaveId;
		}
		
		leave.setStatus(LeaveStatus.DELETED);
		leaveService.updateLeave(leave);
		return "redirect:/staff/leave/overview";
	}
	
}
