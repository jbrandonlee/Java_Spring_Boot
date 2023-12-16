package sg.nus.iss.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.iss.javaproject.exception.LeaveApplicationNotFound;
import sg.nus.iss.javaproject.model.ApprovalStatus;
import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.service.LeaveApplicationService;
import sg.nus.iss.javaproject.service.ILeaveApplication;

import java.util.List;

@Controller
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private ILeaveApplication lService;

    @Autowired
    public void setLeaveService(LeaveApplicationService lImpl) {
        this.lService = lImpl;
    }

    @GetMapping("/toApply")
    public String toApply(Model model){
        model.addAttribute("leaveApplication", new LeaveApplication());
        return "apply";
    }

    @PostMapping("/apply")
    public String submitLeave(@Valid @ModelAttribute("leave") LeaveApplication leaveApplication,
        BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "apply";
        }
        Staff staff = (Staff) session.getAttribute("staff");
        if(staff==null){
            return "login";
        }

        leaveApplication.setLeaveApprovalStatus(ApprovalStatus.applied);
        lService.applyLeaveApplication(leaveApplication);
        return  "redirct:/leave/page/1";
    }

    @GetMapping("/list")
    public String listLeaveApplication(Model model) {
        model.addAttribute("leaveApplication", lService.findAllLeaveApplication());
        return "leaveApplication";
    }

    @RequestMapping(value = "/history")
      public String employeeLeaveHistory(HttpSession session, Model model) {
             Staff staff = (Staff) session.getAttribute("staff");

             System.out.println(staff.getEmployeeId());

             List<LeaveApplication> employeeLeaves = lService.findLeaveApplicationByEID(staff.getEmployeeId());
             model.addAttribute("leaveApplications", employeeLeaves);

             return "leaveApplication";
           }
    @GetMapping("/edit/{id}")
    public String toEditLeaveApplication(@PathVariable Integer id, Model model) {
        LeaveApplication leaveApplication = lService.findLeaveApplicaiton(id);
        model.addAttribute("leaveApplication", leaveApplication);

        return "leave-edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteLeaveApplication(@PathVariable Integer id, Model model) {
        LeaveApplication leaveApplication = lService.findLeaveApplicaiton(id);
        leaveApplication.setLeaveApprovalStatus(ApprovalStatus.deleted);
        return  "redirct:/leave/page/1";
    }
   @PostMapping("/edit")
    public String editLeaveApplication(@ModelAttribute @Valid LeaveApplication leaveApplication, BindingResult result,
         HttpSession session) throws LeaveApplicationNotFound {
        if (result.hasErrors()) {
            return "leave-edit";
        }
        System.out.println("DATES****" + leaveApplication.getLeaveStartDate() + leaveApplication.getLeaveEndDate());

       leaveApplication.setLeaveApprovalStatus(ApprovalStatus.updated);
        lService.editLeaveApplication(leaveApplication);

       return "redirect:/leave/page/1";
    }

    @RequestMapping(value = "/cancel/{id}")
    public String cancelApplication(@PathVariable Integer id) {
        LeaveApplication leaveApplication = lService.findLeaveApplicaiton(id);

        leaveApplication.setLeaveApprovalStatus(ApprovalStatus.cancelled);
        lService.editLeaveApplication(leaveApplication);

        String message = "LeaveApplication" + leaveApplication.getLeaveId() + " was successfully canceled.";
        System.out.println(message);

        return "redirect:/leave/page/1";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        if(staff==null){
            return "login";
        }
        //Here we check the role of the user

        Page<LeaveApplication> page = lService.findLeaveApplicationPage(pageNo, 8, "leaveId", "asc");

        List<LeaveApplication> leaveApplications = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("leaveApplications", leaveApplications);
        return "leaveApplication";
    }
}
