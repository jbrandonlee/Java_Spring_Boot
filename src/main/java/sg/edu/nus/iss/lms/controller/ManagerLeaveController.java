package sg.edu.nus.iss.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
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

		model.addAttribute("pendingLeaves", pendingLeaves);

		return "pendingLeaves";

	}
	
	@RequestMapping(value = "/pending/details/edit/{id}")
    public String editProductForm(@PathVariable("id") Integer id, ModelMap model) {
    	
        model.addAttribute("leaveApplication", lService.findByLeaveId(id));        
        return "editLeave";
    }
	
	@PostMapping("/pending/update")
	public String updateLeaveStatus(@ModelAttribute("leaveApplication") LeaveApplication leaveApplication,
	                                @RequestParam("leaveApprovalStatus") String leaveApprovalStatus) {
	
	    if ("approved".equalsIgnoreCase(leaveApprovalStatus.trim())) {
	        leaveApplication.setLeaveApprovalStatus(ApprovalStatus.approved);
	    } else {
	        leaveApplication.setLeaveApprovalStatus(ApprovalStatus.rejected);
	    }

	    lService.updateManagerComment(leaveApplication);

	    return "redirect:/manager/pending";
	}


	@GetMapping("/history")
	public String listLeavesHistory(HttpSession session, Model model) {

		Staff staff = (Staff) session.getAttribute("staff");

		List<LeaveApplication> leavesHistory = lService.findLeavesHistory(staff.getEmployeeId(),
				ApprovalStatus.applied);

		model.addAttribute("leavesHistory", leavesHistory);

		return "leavesHistory";

	}

}
