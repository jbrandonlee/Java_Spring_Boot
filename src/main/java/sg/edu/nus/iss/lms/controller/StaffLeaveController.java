package sg.edu.nus.iss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.model.Leave.LeaveStatus;
import sg.edu.nus.iss.lms.service.LeaveService;
import sg.edu.nus.iss.lms.service.LeaveTypeService;


@Controller
@RequestMapping(value = "/staff")
public class StaffLeaveController {
	
	@Autowired
	LeaveService leaveService;
	
	@Autowired
	LeaveTypeService leaveTypeService;

	@GetMapping(value = { "/leave/overview"})
	public String staffHome(Model model) {
		return "leave-overview";
	}
	
	// CREATE
	@GetMapping(value = { "/leave/apply"})
	public String staffLeaveApplicationForm(Model model) {
		model.addAttribute("leaveTypes", leaveTypeService.getTypeNames());
		return "leave-apply";
	}
	
	@PostMapping(value = { "/leave/apply"})
	public String staffApplyLeave(@Valid @ModelAttribute("leave") Leave leaveForm, BindingResult bindingResult, Model model) {
		// Add Backend Validation
		// Get Employee from Session
		leaveForm.setEmployee(new Employee());
		leaveForm.setStatus(LeaveStatus.APPLIED);
		leaveService.createLeave(leaveForm);
		
		return "redirect:/staff/leave/overview";
	}
	
	// RETRIEVE LIST
	// Do not retrieve 'LeaveStatus.DELETED'
	@GetMapping(value = { "/leave/history"})
	public String staffLeaveHistory(Model model) {
		return "leave-history";
	}
	
	// RETRIEVE ONE
	// Button for EDIT & RESET
	// PlainText Fields
	@GetMapping(value = { "/leave/details/{id}"})
	public String staffLeaveDetail(Model model) {
		return "leave-details";
	}
	
	// UPDATE or DELETE
	// Update LeaveStatus -> UPDATED or DELETED or CANCELLED (if APPROVED)
	// Show LeaveStatus at Top
	@GetMapping(value = { "/leave/edit/{id}"})
	public String staffLeaveEditForm(Model model) {
		return "leave-edit";
	}

	
	@PostMapping(value = { "/leave/edit/{id}"})
	public String staffEditLeave(Model model) {
		return "redirect:/staff/leave/overview";
	}

}
