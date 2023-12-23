package sg.edu.nus.iss.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.lms.model.ApprovalStatus;
import sg.edu.nus.iss.lms.model.LeaveApplication;
import sg.edu.nus.iss.lms.model.Staff;
import sg.edu.nus.iss.lms.service.LeaveService;
import sg.edu.nus.iss.lms.service.LeaveServiceImpl;

@Controller
@RequestMapping("/manager")
public class ManagerLeaveController {

	@Autowired
	private LeaveService lService;

	@Autowired
	public void setLeaveService(LeaveServiceImpl lServiceImpl) {
		this.lService = lServiceImpl;
	}

	@RequestMapping("/home")
	public String displayHomePage(Model model) {
		return "homePage";
	}

	@GetMapping("/pending")
	public String listPeningLeaves(HttpSession session, Model model) {

		Staff staff = (Staff) session.getAttribute("staff");

		List<LeaveApplication> pendingLeaves = lService.findPendingLeaves(staff.getEmployeeId(),
				ApprovalStatus.applied);
		
		Map<String, List<LeaveApplication>> pendingLeavesByEmployee = new HashMap<>();

		for (LeaveApplication leaveApp : pendingLeaves) {
		    String employeeName = leaveApp.getStaff().getEmployeeName();

		    if (!pendingLeavesByEmployee.containsKey(employeeName)) {
		        pendingLeavesByEmployee.put(employeeName, new ArrayList<>());
		    }

		    pendingLeavesByEmployee.get(employeeName).add(leaveApp);
		}

		model.addAttribute("pendingLeaves", pendingLeavesByEmployee);

		return "pendingLeaves";

	}
	
	@GetMapping("/history")
	public String listLeavesHistory(HttpSession session, Model model) {

		Staff staff = (Staff) session.getAttribute("staff");

		List<LeaveApplication> leavesHistory = lService.findLeavesHistory(staff.getEmployeeId(),
				ApprovalStatus.applied);
		
		Map<String, List<LeaveApplication>> leavesHistoryByEmployee = new HashMap<>();

		for (LeaveApplication leaveApp : leavesHistory) {
		    String employeeName = leaveApp.getStaff().getEmployeeName();

		    if (!leavesHistoryByEmployee.containsKey(employeeName)) {
		    	leavesHistoryByEmployee.put(employeeName, new ArrayList<>());
		    }

		    leavesHistoryByEmployee.get(employeeName).add(leaveApp);
		}
		
		model.addAttribute("leavesHistory", leavesHistoryByEmployee);

		return "leavesHistory";

	}
	
	@RequestMapping("/pending/details/{id}")
    public String editProductForm(@PathVariable("id") int id, Model model) {
    	
		LeaveApplication leaveApplication = lService.findByLeaveId(id);
		
		List<LeaveApplication> overlappingLeaves = lService.getOverlappingLeaves(leaveApplication.getLeaveStartDate(), leaveApplication.getLeaveEndDate());

        model.addAttribute("leaveApplication", leaveApplication); 
        
        model.addAttribute("overlappingLeaves", overlappingLeaves);
        
        return "editLeave";
    }
	
	@PostMapping("/pending/update")
	public String updateLeaveStatus(@ModelAttribute("leaveApplication") @Valid LeaveApplication leaveApplication, BindingResult bindingResult, @RequestParam("leaveApprovalStatus") String leaveApprovalStatus) {
		
		
		if (bindingResult.hasErrors()) {
			 System.out.println(leaveApplication);
			 return "editLeave";
		}
		
	    if ("approved".equalsIgnoreCase(leaveApprovalStatus.trim())) {
	    	
	    	leaveApplication.setLeaveApprovalStatus(ApprovalStatus.approved);
	    	
	    } else {
	    	
	        leaveApplication.setLeaveApprovalStatus(ApprovalStatus.rejected);
	    }
	    
	    System.out.println(leaveApplication);
	
	    lService.save(leaveApplication);  

	    return "redirect:/manager/pending";
	}

}
