package sg.nus.iss.javaproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.iss.javaproject.model.ApprovalStatus;
import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.service.LeaveApplicationImplementation;
import sg.nus.iss.javaproject.service.LeaveApplicationInterface;
import sg.nus.iss.javaproject.validator.LeaveValidator;

@Controller
public class LeaveController {
	@Autowired
	private LeaveApplicationInterface lService;
	
	@Autowired
	public void setLeaveService(LeaveApplicationImplementation lImpl) {
		this.lService=lImpl;
	}
	
	@Autowired
	private LeaveValidator leaveValidator;
	
	@InitBinder
	private void initCourseBinder(WebDataBinder binder) {
	   binder.addValidators(leaveValidator);
	}
	
	@RequestMapping("/leave")
	public String leaveStatus(Model model,HttpSession session) {
		Staff staff=(Staff)session.getAttribute("staff");
		
		int id=staff.getEmployeeId();
		String name=staff.getEmployeeName();
		
		List<LeaveApplication> allApplications=lService.getAllLeaveByStaffId(id);
		model.addAttribute("name", name);
		model.addAttribute("allApplications",allApplications);
		
		return "leaveStatus";
	}
	
	@GetMapping("/leave/apply")
	public String applyLeave(Model model,HttpSession session) {
		LeaveApplication leaveApplication=new LeaveApplication();
		Staff staff=(Staff)session.getAttribute("staff");
		switch(staff.getPosition()){
			case "administrative":leaveApplication.setLeaveDays(14);
			case "professional":leaveApplication.setLeaveDays(18);
		}
		model.addAttribute("leave",leaveApplication);
		
		return "apply";
	}
	
	@PostMapping("/leave/apply")
	public String submitLeave(@Valid @ModelAttribute("leave")LeaveApplication leaveApplication ,BindingResult bindingResult,HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "apply";
		}		
		Staff staff=(Staff)session.getAttribute("staff");
		leaveApplication.setLeaveApprovalStatus(ApprovalStatus.applied);
		leaveApplication.setStaff(staff);
		lService.saveLeaveApplication(leaveApplication);
		
		return "redirect:/leave";
	}
	
	@RequestMapping(value="/leave/edit/{leaveId}")
	public String updateApply(@PathVariable("leaveId") Integer id, ModelMap model) {
		model.addAttribute("leave", lService.findLeaveById(id));
		return "editLeave";
	}
	
	@PostMapping("/leave/edit")
	public String updateStatus(@Valid @ModelAttribute("leave")LeaveApplication leaveApplication ,BindingResult bindingResult,HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "editLeave";
		}
		Staff staff=(Staff)session.getAttribute("staff");
		leaveApplication.setLeaveApprovalStatus(ApprovalStatus.updated);
		leaveApplication.setStaff(staff);
		lService.saveLeaveApplication(leaveApplication);
		return "redirect:/leave";
	}
	
	@RequestMapping(value="/leave/delete/{leaveId}")
	public String deleteApply(@PathVariable("leaveId") Integer id, ModelMap model) {
		model.addAttribute("leave", lService.findLeaveById(id));
		return "deleteLeave";
	}
	
	@PostMapping("/leave/delete")
	public String deleteStatus(@ModelAttribute("leave")LeaveApplication leaveApplication,HttpSession session) {
		Staff staff=(Staff)session.getAttribute("staff");
		leaveApplication.setLeaveApprovalStatus(ApprovalStatus.deleted);
		leaveApplication.setStaff(staff);
		lService.saveLeaveApplication(leaveApplication);
		return "redirect:/leave";
	}
	
	@RequestMapping(value="/leave/cancel/{leaveId}")
	public String cancelApply(@PathVariable("leaveId") Integer id, ModelMap model) {
		model.addAttribute("leave", lService.findLeaveById(id));
		return "cancelLeave";
	}
	
	@PostMapping("/leave/cancel")
	public String cancelStatus(@ModelAttribute("leave")LeaveApplication leaveApplication,HttpSession session) {
		Staff staff=(Staff)session.getAttribute("staff");
		leaveApplication.setLeaveApprovalStatus(ApprovalStatus.cancelled);
		leaveApplication.setStaff(staff);
		lService.saveLeaveApplication(leaveApplication);
		return "redirect:/leave";
	}
}
