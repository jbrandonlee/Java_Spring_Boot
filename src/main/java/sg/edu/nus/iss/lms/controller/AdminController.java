package sg.edu.nus.iss.lms.controller;

import java.util.ArrayList;
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
import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Holiday;
import sg.edu.nus.iss.lms.model.LeaveEntitlement;
import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.model.Role;
import sg.edu.nus.iss.lms.service.AccountService;
import sg.edu.nus.iss.lms.service.EmployeeService;
import sg.edu.nus.iss.lms.service.HolidayService;
import sg.edu.nus.iss.lms.service.LeaveEntitlementService;
import sg.edu.nus.iss.lms.service.LeaveTypeService;
import sg.edu.nus.iss.lms.service.RoleService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	LeaveEntitlementService leaveEntService;
	
	@Autowired
	LeaveTypeService leaveTypeService;
	
	@Autowired
	HolidayService holidayService;
	
	@Autowired
	RoleService roleService;
	
	// -----------------------
	// -- Manage Staff List --
	// -----------------------
	@GetMapping(value = {"", "/", "/staff"})
	public String staffList(Model model, HttpSession sessionObj) {
		List<Employee> staffList = employeeService.findAllEmployees();
		model.addAttribute("staffList", staffList);
		return "admin-staff-list";
	}
	
	@GetMapping(value = "/staff/create")
	public String staffCreateForm(Model model, HttpSession sessionObj) {
		List<Integer> managerIdList = employeeService.findAllManagerIDs();
		model.addAttribute("employee", new Employee());
		model.addAttribute("managerIdList", managerIdList);
		return "admin-staff-create";
	}
	
	@PostMapping(value = "/staff/create")
	public String staffCreate(@Valid @ModelAttribute("staff") Employee employee, BindingResult bindingResult,
			Model model, HttpSession sessionObj) {
		
		if (bindingResult.hasErrors()) {
			List<Integer> managerIdList = employeeService.findAllManagerIDs();
			model.addAttribute("employee", new Employee());
			model.addAttribute("managerIdList", managerIdList);
			return "admin-staff-create";
		}
		
		employeeService.createEmployee(employee);
		return "redirect:/admin/staff";
	}
	
	@GetMapping(value = "/staff/edit/{id}")
	public String staffEditForm(@PathVariable(name="id") Integer employeeId,
			Model model, HttpSession sessionObj) {
		// Admin can edit staff details
		return "admin-staff-edit";
	}
	
	@PostMapping(value = "/staff/edit/{id}")
	public String staffEdit(@PathVariable(name="id") Integer employeeId,
			Model model, HttpSession sessionObj) {

		return "redirect:/admin/staff";
	}
	
	
	// -------------------------
	// -- Manage Account List --
	// -------------------------
	@GetMapping(value = "/account")
	public String accountList(Model model, HttpSession sessionObj) {
		List<Account> accountList = accountService.findAllAccounts();
		model.addAttribute("accountList", accountList);
		return "admin-account-list";
	}
	
	@GetMapping(value = "/account/create")
	public String accountCreateForm(Model model, HttpSession sessionObj) {
		List<Role> roleList = roleService.findAllRoles();
		List<Integer> employeeIdList = employeeService.findAllEmployeeIDNoAccount();
		model.addAttribute("account", new Account());
		model.addAttribute("roleList", roleList);
		model.addAttribute("employeeIdList", employeeIdList);
		return "admin-account-create";
	}
	
	@PostMapping(value = "/account/create")
	public String accountCreate(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult,
			Model model, HttpSession sessionObj) {
		
		if (bindingResult.hasErrors()) {
			List<Role> roleList = roleService.findAllRoles();
			List<Integer> employeeIdList = employeeService.findAllEmployeeIDNoAccount();
			model.addAttribute("account", new Account());
			model.addAttribute("roleList", roleList);
			model.addAttribute("employeeIdList", employeeIdList);
			return "admin-account-create";
		}
		
		List<Role> roles = new ArrayList<Role>();
	    account.getRoles().forEach(role -> {
	      Role completeRole = roleService.findRoleById(role.getId());
	      roles.add(completeRole);
	    });

	    account.setRoles(roles);
		account.setEmployee(employeeService.findEmployeeById(account.getEmployee().getId()));
		accountService.createAccount(account);
		
		return "redirect:/admin/account";
	}
	
	@GetMapping(value = "/account/edit/{id}")
	public String accountEditForm(@PathVariable(name="id") String accountId,
			Model model, HttpSession sessionObj) {
		// Admin can edit account details (username/pass/roles)
		return "admin-account-edit";
	}
	
	@PostMapping(value = "/account/edit/{id}")
	public String accountEdit(@PathVariable(name="id") String accountId,
			Model model, HttpSession sessionObj) {

		return "redirect:/admin/account";
	}
	
	@PostMapping(value = "/account/delete/{id}")
	public String accountDelete(@PathVariable(name="id") String accountId,
			Model model, HttpSession sessionObj) {
		// Admin can delete an account tied to a staff, staff is not affected
		return "redirect:/admin/account";
	}

	
	// -----------------------------------
	// -- Manage Staff LeaveEntitlement --
	// -----------------------------------
	@GetMapping(value = "/leave_entitlement")
	public String leaveEntitlementList(Model model, HttpSession sessionObj) {
		List<LeaveEntitlement> leaveEntList = leaveEntService.findAllLeaveEnts();
		model.addAttribute("leaveEntList", leaveEntList);
		return "admin-leave-entitlement-list";
	}
	
	@GetMapping(value = "/leave_entitlement/create")
	public String leaveEntitlementCreateForm(Model model, HttpSession sessionObj) {
		List<Integer> employeeIdList = employeeService.findAllEmployeeIDs();
		model.addAttribute("leaveEntitlement", new LeaveEntitlement());
		model.addAttribute("employeeIdList", employeeIdList);
		model.addAttribute("leaveTypes", leaveTypeService.getTypeNames());
		return "admin-leave-entitlement-create";
	}
	
	@PostMapping(value = "/leave_entitlement/create")
	public String leaveEntitlementCreate(@Valid @ModelAttribute("leaveEntitlement") LeaveEntitlement leaveEnt, BindingResult bindingResult,
			Model model, HttpSession sessionObj) {
		
		if (bindingResult.hasErrors()) {
			List<Integer> employeeIdList = employeeService.findAllEmployeeIDs();
			model.addAttribute("leaveEntitlement", new LeaveEntitlement());
			model.addAttribute("employeeIdList", employeeIdList);
			model.addAttribute("leaveTypes", leaveTypeService.getTypeNames());
			return "admin-leave-entitlement-create";
		}
		
		return "redirect:/admin/leave_entitlement";
	}
	
	@GetMapping(value = "/leave_entitlement/edit/{id}")
	public String leaveEntitlementEditForm(@PathVariable(name="id") Integer leaveEntId,
			Model model, HttpSession sessionObj) {
		// Admin can edit staff account details
		return "admin-leave-entitlement-edit";
	}
	
	@PostMapping(value = "/leave_entitlement/edit/{id}")
	public String leaveEntitlementEdit(@PathVariable(name="id") Integer leaveEntId,
			Model model, HttpSession sessionObj) {

		return "redirect:/admin/leave_entitlement";
	}

	
	// -----------------------
	// -- Manage LeaveTypes --
	// -----------------------
	@GetMapping(value = "/leave_type")
	public String leaveTypeList(Model model, HttpSession sessionObj) {
		List<LeaveType> leaveTypeList = leaveTypeService.findAllLeaveTypes();
		model.addAttribute("leaveTypeList", leaveTypeList);
		return "admin-leave-type-list";
	}
	
	@GetMapping(value = "/leave_type/create")
	public String leaveTypeCreateForm(Model model, HttpSession sessionObj) {
		model.addAttribute("leaveType", new LeaveType());
		return "admin-leave-type-create";
	}
	
	@PostMapping(value = "/leave_type/create")
	public String leaveTypeCreate(@Valid @ModelAttribute("leaveType") LeaveType leaveType, BindingResult bindingResult,
			Model model, HttpSession sessionObj) {

		if (bindingResult.hasErrors()) {
			return "admin-leave-type-create";
		}
		
		return "redirect:/admin/leave_type";
	}
	
	@GetMapping(value = "/leave_type/edit/{id}")
	public String leaveTypeEditForm(@PathVariable(name="id") Integer leaveTypeId,
			Model model, HttpSession sessionObj) {
		// Admin can edit staff account details
		return "admin-leave-type-edit";
	}
	
	@PostMapping(value = "/leave_type/edit/{id}")
	public String leaveTypeEdit(@PathVariable(name="id") Integer leaveTypeId,
			Model model, HttpSession sessionObj) {

		return "redirect:/admin/leave_type";
	}
	
	
	// ---------------------
	// -- Manage Holidays --
	// ---------------------
	@GetMapping(value = "/holiday")
	public String holidayList(Model model, HttpSession sessionObj) {
		List<Holiday> holidayList = holidayService.findAllHolidays();
		model.addAttribute("holidayList", holidayList);
		return "admin-holiday-list";
	}
	
	@GetMapping(value = "/holiday/create")
	public String holidayCreateForm(Model model, HttpSession sessionObj) {
		model.addAttribute("holiday", new Holiday());
		return "admin-holiday-create";
	}
	
	@PostMapping(value = "/holiday/create")
	public String holidayCreate(@Valid @ModelAttribute("holiday") Holiday holiday, BindingResult bindingResult,
			Model model, HttpSession sessionObj) {

		if (bindingResult.hasErrors()) {
			return "admin-holiday-create";
		}
		
		return "redirect:/admin/holiday";
	}
	
	@GetMapping(value = "/holiday/edit/{id}")
	public String holidayEditForm(@PathVariable(name="id") Integer holidayId,
			Model model, HttpSession sessionObj) {
		// Admin can edit holiday date / observed date
		return "admin-holiday-edit";
	}
	
	@PostMapping(value = "/holiday/edit/{id}")
	public String holidayEdit(@PathVariable(name="id") Integer holidayId,
			Model model, HttpSession sessionObj) {

		return "redirect:/admin/holiday";
	}
	
	@PostMapping(value = "/holiday/delete/{id}")
	public String holidayDelete(@PathVariable(name="id") Integer holidayId,
			Model model, HttpSession sessionObj) {

		return "redirect:/admin/holiday";
	}
}
