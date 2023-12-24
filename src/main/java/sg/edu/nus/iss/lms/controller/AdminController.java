package sg.edu.nus.iss.lms.controller;

import java.util.*;

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
import sg.edu.nus.iss.lms.model.*;
import sg.edu.nus.iss.lms.service.EmployeeService;
import sg.edu.nus.iss.lms.service.LeaveEntitlementService;
import sg.edu.nus.iss.lms.service.LeaveTypeService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private LeaveEntitlementService leaveEntitlementService;

    // -----------------------
    // -- Manage Staff List --
    // -----------------------
    @GetMapping(value = "/staff")
    public String staffList(Model model, HttpSession sessionObj) {
        // Admin can see list of staff, edit staff, create new staff
        List<Employee> staffList = employeeService.findAll();
        model.addAttribute("staffList", staffList);
        return "admin-staff-list";
    }

    @GetMapping(value = "/staff/create")
    public String staffCreateForm(Model model, HttpSession sessionObj) {
        // Admin can create staff from empty form
        model.addAttribute("employee", new Employee());
        return "admin-staff-create";
    }

    @PostMapping(value = "/staff/createStaff")
    public String staffCreate(@Valid @ModelAttribute("employee") Employee employee, Model model, HttpSession sessionObj) {
//        employeeService.save(employee);
        return "redirect:/admin/staff";
    }

    @GetMapping(value = "/staff/{id}/edit")
    public String staffEditForm(Model model, HttpSession sessionObj) {
        // Admin can edit staff details
        return "admin-staff-edit";
    }

    @PostMapping(value = "/staff/editOne/{id}")
    public String staffEdit(@Valid @PathVariable Integer id, @ModelAttribute("employee") Employee employee,Model model, HttpSession sessionObj) {
        Employee employee1 = employeeService.findEmployeeById(id);
        employee1.setJobDesignation(employee.getJobDesignation());
        employee1.setName(employee1.getName());
        employeeService.create(employee1);
        return "redirect:/admin/staff";
    }


    // -------------------------
    // -- Manage Account List --
    // -------------------------
    @GetMapping(value = {"", "/", "/account"})
    public String accountList(Model model, HttpSession sessionObj) {
        // Admin can see list of accounts, edit accounts, create new account
        return "admin-account-list";
    }

    @GetMapping(value = "/account/{id}/create")
    public String accountCreateForm(Model model, HttpSession sessionObj) {
        // Admin can create account from empty form, assign staff to it, set roles
        return "admin-account-create";
    }

    @PostMapping(value = "/account/{id}/create")
    public String accountCreate(Model model, HttpSession sessionObj) {

        return "redirect:/admin/account";
    }

    @GetMapping(value = "/account/{id}/edit")
    public String accountEditForm(Model model, HttpSession sessionObj) {
        // Admin can edit account details (username/pass/roles)
        return "admin-account-edit";
    }

    @PostMapping(value = "/account/{id}/edit")
    public String accountEdit(Model model, HttpSession sessionObj) {

        return "redirect:/admin/account";
    }

    @PostMapping(value = "/account/{id}/delete")
    public String accountDisable(Model model, HttpSession sessionObj) {
        // Admin can delete an account tied to a staff, staff is not affected
        return "redirect:/admin/account";
    }


    // -----------------------------------
    // -- Manage Staff LeaveEntitlement --
    // -----------------------------------
    @GetMapping(value = "/leaveentitlement/")
    public String leaveEntitlementList(Model model, HttpSession sessionObj) {
        // Admin can see list of staff leave entitlements, edit, create
        // Group by Staff
        List<LeaveEntitlement> leaveEntitlements = leaveEntitlementService.findAll();
        model.addAttribute("leaveEntitlements", leaveEntitlements);
        return "admin-leaveentitlement-list";
    }

    @GetMapping(value = "/leaveentitlement/{id}")
    public String leaveEntitlementListById(@PathVariable(name = "id") Integer employeeId, Model model, HttpSession sessionObj) {
        // Admin can see list of staff leave entitlements, edit, create
        // Group by Staff
        List<LeaveEntitlement> leaveEntitlements = leaveEntitlementService.findByEmployeeId(employeeId);
        model.addAttribute("leaveEntitlements", leaveEntitlements);
        return "admin-leaveentitlement-list";
    }

    @GetMapping(value = "/leaveentitlement/create")
    public String leaveEntitlementCreateForm(Model model, HttpSession sessionObj) {
        // Admin can create leave entitlement and assign to staff
        // If staff already has existing entitlement of type, reject
        LeaveEntitlement leaveEntitlement = new LeaveEntitlement();
        model.addAttribute("leaveEntitlement", leaveEntitlement);
        return "admin-leaveentitlement-create";
    }

    @PostMapping(value = "/leaveentitlement/create")
    public String leaveEntitlementCreate(@ModelAttribute("leaveEntitlement") LeaveEntitlement leaveEntitlement, Model model, HttpSession sessionObj) {
        leaveEntitlementService.save(leaveEntitlement);
        return "redirect:/admin/leaveentitlement";
    }

    @GetMapping(value = "/leaveentitlement/{id}/edit")
    public String leaveEntitlementEditForm(@PathVariable Integer id, Model model, HttpSession sessionObj) {
        // Admin can edit staff account details
        Optional<LeaveEntitlement> leaveEntitlement = leaveEntitlementService.findById(id);
        model.addAttribute("leaveEntitlement", leaveEntitlement);
        return "admin-leaveentitlement-edit";
    }

    @PostMapping(value = "/leaveentitlement/edit/{id}/{employeeId}")
    public String leaveEntitlementEdit(@PathVariable Integer id, @PathVariable Integer employeeId, @ModelAttribute LeaveEntitlement leaveEntitlement, Model model, HttpSession sessionObj) {

        Optional<LeaveEntitlement> leaveEntitlementOptional = leaveEntitlementService.findById(id);
        LeaveEntitlement currentLeaveEntitlement = leaveEntitlementOptional.get();
        currentLeaveEntitlement.setLeaveBalance(leaveEntitlement.getLeaveBalance());
        currentLeaveEntitlement.setEntitlement(leaveEntitlement.getEntitlement());
        leaveEntitlementService.save(currentLeaveEntitlement);
        List<LeaveEntitlement> leaveEntitlements = leaveEntitlementService.findByEmployeeId(employeeId);
        model.addAttribute("leaveEntitlements", leaveEntitlements);
        return "admin-leaveentitlement-list";
    }


    // -----------------------
    // -- Manage LeaveTypes --
    // -----------------------
    @GetMapping(value = "/leavetype")
    public String leaveTypeList(Model model, HttpSession sessionObj) {
        // Admin can see list of leavetypes
        List<LeaveType> leaveTypeList = leaveTypeService.findAll();
        model.addAttribute("leaveTypeList", leaveTypeList);
        return "admin-leavetype-list";
    }

    @GetMapping(value = "/leavetype/createPage")
    public String leaveTypeCreateForm(Model model, HttpSession sessionObj) {
        // Admin can create leave type
        model.addAttribute("leaveType", new LeaveType());
        return "admin-leavetype-create";
    }

    @PostMapping(value = "/leavetype/create")
    public String leaveTypeCreate(@Valid @ModelAttribute("leaveType") LeaveType leaveType, Model model, HttpSession sessionObj) {

        leaveTypeService.save(leaveType);
        if (leaveType != null) {
            List<LeaveType> leaveTypeList = leaveTypeService.findAll();
            model.addAttribute("leaveTypeList", leaveTypeList);
            return "admin-leavetype-list";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping(value = "/leavetype/editOne/{id}")
    public String leaveTypeEditForm(@PathVariable Integer id, Model model, HttpSession sessionObj) {
        // Admin can edit staff account details
        LeaveType leaveType = leaveTypeService.findById(id);
        if (leaveType != null) {
            model.addAttribute("leaveType", leaveType);
            return "admin-leavetype-edit";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping(value = "/leavetype/deleteOne/{id}")
    public String leaveTypeDelete(@PathVariable Integer id, Model model, HttpSession sessionObj) {
        // Admin can edit staff account details
        leaveTypeService.deleteOneById(id);
        List<LeaveType> leaveTypeList = leaveTypeService.findAll();
        model.addAttribute("leaveTypeList", leaveTypeList);
        return "admin-leavetype-list";
    }

    @PostMapping(value = "/leavetype/edit/{id}")
    public String leaveTypeEdit(@PathVariable Integer id, @ModelAttribute("leaveType") LeaveType leaveType, Model model, HttpSession sessionObj) {
        LeaveType updatedLeaveType = leaveTypeService.update(id, leaveType);
        if (updatedLeaveType != null) {
            List<LeaveType> leaveTypeList = leaveTypeService.findAll();
            model.addAttribute("leaveTypeList", leaveTypeList);
            return "admin-leavetype-list";
        } else {
            return "redirect:/error";
        }
    }


    // ---------------------
    // -- Manage Holidays --
    // ---------------------
    @GetMapping(value = "/holiday")
    public String holidayList(Model model, HttpSession sessionObj) {
        // Admin can see list of holidays
        return "admin-holiday-list";
    }

    @GetMapping(value = "/holiday/{id}/create")
    public String holidayCreateForm(Model model, HttpSession sessionObj) {
        // Admin can create new holiday
        return "admin-holiday-create";
    }

    @PostMapping(value = "/holiday/{id}/create")
    public String holidayCreate(Model model, HttpSession sessionObj) {

        return "redirect:/admin/holiday";
    }

    @GetMapping(value = "/holiday/{id}/edit")
    public String holidayEditForm(Model model, HttpSession sessionObj) {
        // Admin can edit holiday date / observed date
        return "admin-holiday-edit";
    }

    @PostMapping(value = "/holiday/{id}/edit")
    public String holidayEdit(Model model, HttpSession sessionObj) {

        return "redirect:/admin/holiday";
    }

    @PostMapping(value = "/holiday/{id}/delete")
    public String holidayDelete(Model model, HttpSession sessionObj) {

        return "redirect:/admin/holiday";
    }
}
