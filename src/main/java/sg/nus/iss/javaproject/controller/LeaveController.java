package sg.nus.iss.javaproject.controller;

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
import sg.nus.iss.javaproject.exception.LeaveApplicationNotFound;
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
		leaveApplication.setLeaveApprovalStatus(ApprovalStatus.APPLIED);
		return "leaveStatus";
	}
	
	 @GetMapping("/list")
	    public String listLeaveApplication(Model model) {
	        model.addAttribute("leaveApplication", lService.findAllLeaveApplication());
	        return "leaveApplication";
	 }
	 /*UserSession我不会写，
	 @RequestMapping(value = "leave/history")
	   public String employeeLeaveHistory(HttpSession session, Model model) {
	          UserSession usession = (UserSession) session.getAttribute("usession");
	          
	          System.out.println(usession.getEmployee());
	          
	          List<LeaveApplication> employeeLeaves = lService.findLeaveApplByEID(usession.getEmployee().getEmployeeId());
	          model.addAttribute("lhistory", employeeLeaves);
	            
	          return "leave-my-history";
	        }*/
	        @GetMapping("/leave/edit/{id}")
	        public String editCoursePage(@PathVariable Integer id, Model model) {
			LeaveApplication leaveApplication = lService.findLeaveApplicaiton(employeeId);
	          model.addAttribute("leave", leaveApplication);
	          
	          return "leave-edit";
	        }

	        @PostMapping("/leave/edit/{id}")
	        public String editCourse(@ModelAttribute @Valid LeaveApplication leaveApplication, BindingResult result, 
	        		@PathVariable Integer id,HttpSession session) throws LeaveApplicationNotFound {
	          if (result.hasErrors())
	            return "leave-edit";
	          
	           System.out.println("DATES****" + leaveApplication.getLeaveStartDate() + leaveApplication.getLeaveEndDate());
	          
	          //UserSession usession = (UserSession) session.getAttribute("usession");
	          //leaveApplication.setEmployeeId(usession.getEmployee().getEmployeeId());
	          leaveApplication.setLeaveApprovalStatus(ApprovalStatus.UPDATED);
	          
	          lService.editLeaveApplication(leaveApplication);
	          
	          return "redirect:/employee/leave/history";
	        }
	        
	        @RequestMapping(value = "/leave/cancel/{id}")
	        public String deleteCourse(@PathVariable Integer id) throws LeaveApplicationNotFound {
	          LeaveApplication leaveApplication = lService.findLeaveApplicaiton(employeeId);
	          
	          leaveApplication.setLeaveApprovalStatus(ApprovalStatus.CANCELLED);
	          lService.editLeaveApplication(leaveApplication);

	          String message = "LeaveApplication" + leaveApplication.getLeaveId() + " was successfully cancelled.";
	          System.out.println(message);
	          
	          return "redirect:/employee/leave/history";
	        }
        
}
