package sg.edu.nus.iss.lms;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Holiday;
import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.model.Leave.DaySection;
import sg.edu.nus.iss.lms.model.Leave.LeaveStatus;
import sg.edu.nus.iss.lms.model.LeaveEntitlement;
import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.model.OvertimeClaim;
import sg.edu.nus.iss.lms.model.OvertimeClaim.ClaimStatus;
import sg.edu.nus.iss.lms.model.Role;
import sg.edu.nus.iss.lms.repository.AccountRepository;
import sg.edu.nus.iss.lms.repository.EmployeeRepository;
import sg.edu.nus.iss.lms.repository.HolidayRepository;
import sg.edu.nus.iss.lms.repository.LeaveEntitlementRepository;
import sg.edu.nus.iss.lms.repository.LeaveRepository;
import sg.edu.nus.iss.lms.repository.LeaveTypeRepository;
import sg.edu.nus.iss.lms.repository.OvertimeRepository;
import sg.edu.nus.iss.lms.repository.RoleRepository;

@SpringBootApplication
public class LeaveManagementSystemApplication {
	
	@Autowired
	HolidayRepository holidayRepo;
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(LeaveManagementSystemApplication.class, args);
	}

    @Bean
    CommandLineRunner loadData(AccountRepository accRepo, EmployeeRepository empRepo,
    						   RoleRepository roleRepo, LeaveRepository leaveRepo,
    						   LeaveTypeRepository leaveTypeRepo, 
    						   LeaveEntitlementRepository leaveEntitlementRepo,
    						   OvertimeRepository overtimeRepo) {
		return args -> {

			// Initialize LeaveTypes
			LeaveType annual = leaveTypeRepo.save(new LeaveType("Annual"));
			LeaveType medical = leaveTypeRepo.save(new LeaveType("Medical"));
			LeaveType compensation = leaveTypeRepo.save(new LeaveType("Compensation"));
			
			// Initialize Roles
			Role admin = roleRepo.save(new Role("admin", "Admin", "System Administrator"));
			Role manager = roleRepo.save(new Role("manager", "Manager", "Manager"));
			Role staff = roleRepo.save(new Role("staff", "Staff", "Staff Member"));
			
			// Create Role List for Account initialization
			List<Role> allRoles = new ArrayList<Role>() {{ add(admin); add(manager); add(staff); }};
			List<Role> managerRoles = new ArrayList<Role>() {{ add(manager); add(staff); }};
			List<Role> staffRoles = new ArrayList<Role>() {{ add(staff); }};
			
			// Initialize Employees
			Employee boss = empRepo.save(new Employee("Esther","Boss", LocalDate.of(2023, 01, 01), null));
			Employee manager1 = empRepo.save(new Employee("Tin","Professional Manager", LocalDate.of(2023, 02, 01), 1));
			Employee manager2 = empRepo.save(new Employee("Yuen Kwan","Admin Manager", LocalDate.of(2023, 02, 01), 1));
			Employee staff1a = empRepo.save(new Employee("Lee Junhui Brandon","Professional Staff", LocalDate.of(2023, 03, 01), 2));
			Employee staff1b = empRepo.save(new Employee("Phyo Khant Ko","Professional Staff", LocalDate.of(2023, 04, 01), 2));
			Employee staff2a = empRepo.save(new Employee("Shen Yu","Administrative Staff", LocalDate.of(2023, 03, 01), 3));
			Employee staff2b = empRepo.save(new Employee("Hai Xin Jie","Administrative Staff", LocalDate.of(2023, 04, 01), null));
			
			// Create Accounts for Employees
			// -- Accounts (for Login) are not created for the rest
			accRepo.save(new Account("boss", "password1", boss, allRoles));
			accRepo.save(new Account("manager1", "password1", manager1, managerRoles));
			accRepo.save(new Account("staff1a", "password1", staff1a, staffRoles));
			accRepo.save(new Account("staff2a", "password1", staff2a, staffRoles));
			
			// Create LeaveEntitlement for all Employees with Accounts
			// -- In Real Situation, all Employees should have LeaveEntitlement			
			leaveEntitlementRepo.save(new LeaveEntitlement(boss, annual, 18));
			leaveEntitlementRepo.save(new LeaveEntitlement(boss, medical, 60));
			leaveEntitlementRepo.save(new LeaveEntitlement(boss, compensation, 0));
			leaveEntitlementRepo.save(new LeaveEntitlement(manager1, annual, 18));
			leaveEntitlementRepo.save(new LeaveEntitlement(manager1, medical, 60));
			leaveEntitlementRepo.save(new LeaveEntitlement(manager1, compensation, 0));
			leaveEntitlementRepo.save(new LeaveEntitlement(staff1a, annual, 18));
			leaveEntitlementRepo.save(new LeaveEntitlement(staff1a, medical, 60));
			leaveEntitlementRepo.save(new LeaveEntitlement(staff1a, compensation, 2));
			leaveEntitlementRepo.save(new LeaveEntitlement(staff2a, annual, 14));
			leaveEntitlementRepo.save(new LeaveEntitlement(staff2a, medical, 60));
			leaveEntitlementRepo.save(new LeaveEntitlement(staff2a, compensation, 0));
			
			// Create Leaves for Manager1, Staff1a, Staff1b, Staff2a
			// -- Note that this will throw ConstraintViolationException if the Leave Dates are not AFTER LocalDate.now().
			leaveRepo.save(new Leave(manager1, medical, LocalDate.of(2024, 2, 2), DaySection.AM, LocalDate.of(2024, 2, 2), DaySection.PM, "Local", "1 Day Medical Leave", "No remaining work.", "NIL", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(manager1, annual, LocalDate.of(2024, 2, 5), DaySection.AM, LocalDate.of(2024, 2, 19), DaySection.PM, "Overseas", "15 Day Leave", "No remaining work.", "Mobile: 81234567", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(manager1, compensation, LocalDate.of(2024, 2, 20), DaySection.AM, LocalDate.of(2024, 2, 20), DaySection.AM, "Local", "Half-Day Compensation Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			
			leaveRepo.save(new Leave(staff1a, compensation, LocalDate.of(2024, 1, 10), DaySection.AM, LocalDate.of(2024, 1, 10), DaySection.AM, "Local", "Half-Day Compensation Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, medical, LocalDate.of(2024, 1, 11), DaySection.AM, LocalDate.of(2024, 1, 11), DaySection.AM, "Local", "1-Day Medical Appt", "No remaining work.", "NIL", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 1, 12), DaySection.AM, LocalDate.of(2024, 1, 18), DaySection.PM, "Overseas", "1 Week Leave", "No remaining work.", "Mobile: 91234567", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 1, 19), DaySection.AM, LocalDate.of(2024, 1, 19), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.UPDATED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 1, 22), DaySection.AM, LocalDate.of(2024, 1, 22), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.DELETED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 1, 23), DaySection.AM, LocalDate.of(2024, 1, 23), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 1, 24), DaySection.AM, LocalDate.of(2024, 1, 25), DaySection.PM, "Local", "2 Day Leave", "No remaining work.", "NIL", LeaveStatus.REJECTED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 1, 26), DaySection.AM, LocalDate.of(2024, 1, 26), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.CANCELLED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 2, 5), DaySection.AM, LocalDate.of(2024, 2, 5), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 2, 6), DaySection.AM, LocalDate.of(2024, 2, 6), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 2, 7), DaySection.AM, LocalDate.of(2024, 2, 7), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 2, 8), DaySection.AM, LocalDate.of(2024, 2, 8), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 2, 9), DaySection.AM, LocalDate.of(2024, 2, 9), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1a, annual, LocalDate.of(2024, 2, 12), DaySection.AM, LocalDate.of(2024, 2, 26), DaySection.PM, "Overseas", "15 Day Leave", "No remaining work.", "Mobile: 91234567", LeaveStatus.APPLIED));
			overtimeRepo.save(new OvertimeClaim(staff1a, LocalDateTime.of(LocalDate.of(2023, 12, 1), LocalTime.of(18, 0)), LocalDateTime.of(LocalDate.of(2023, 12, 1), LocalTime.of(22, 0)), "4hrs OT", ClaimStatus.APPLIED));
			overtimeRepo.save(new OvertimeClaim(staff1a, LocalDateTime.of(LocalDate.of(2023, 12, 2), LocalTime.of(18, 30)), LocalDateTime.of(LocalDate.of(2023, 12, 3), LocalTime.of(0, 0)), "5.5hrs OT", ClaimStatus.APPLIED));
			overtimeRepo.save(new OvertimeClaim(staff1a, LocalDateTime.of(LocalDate.of(2023, 12, 3), LocalTime.of(9, 0)), LocalDateTime.of(LocalDate.of(2023, 12, 3), LocalTime.of(18, 0)), "9hours OT", ClaimStatus.APPLIED));
			overtimeRepo.save(new OvertimeClaim(staff1a, LocalDateTime.of(LocalDate.of(2023, 12, 4), LocalTime.of(18, 30)), LocalDateTime.of(LocalDate.of(2023, 12, 5), LocalTime.of(0, 0)), "5.5hrs OT", ClaimStatus.REJECTED));
			overtimeRepo.save(new OvertimeClaim(staff1a, LocalDateTime.of(LocalDate.of(2023, 12, 5), LocalTime.of(9, 0)), LocalDateTime.of(LocalDate.of(2023, 12, 5), LocalTime.of(18, 0)), "9hours OT", ClaimStatus.APPROVED));
			
			leaveRepo.save(new Leave(staff1b, annual, LocalDate.of(2024, 1, 10), DaySection.AM, LocalDate.of(2024, 1, 12), DaySection.PM, "Local", "Overlapping Leave", "No remaining work.", "Mobile: 92234567", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(staff1b, annual, LocalDate.of(2024, 1, 16), DaySection.AM, LocalDate.of(2024, 1, 16), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff1b, annual, LocalDate.of(2024, 1, 17), DaySection.AM, LocalDate.of(2024, 1, 17), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.REJECTED));
			leaveRepo.save(new Leave(staff2a, annual, LocalDate.of(2024, 2, 5), DaySection.AM, LocalDate.of(2024, 2, 5), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff2a, annual, LocalDate.of(2024, 2, 6), DaySection.AM, LocalDate.of(2024, 2, 6), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(staff2a, annual, LocalDate.of(2024, 2, 7), DaySection.AM, LocalDate.of(2024, 2, 7), DaySection.PM, "Local", "1 Day Leave", "No remaining work.", "NIL", LeaveStatus.APPLIED));
			
			/* -- Add Holiday Mock Data from 2022-2024 --
			 * Free access to the API only gives data for 2022.
			 * 2023 & 2024 mock data are duplicated from 2022 data.
			 */
			/*
		    for (Holiday holiday : getHolidaysFromAPI()) {
		    	holidayRepo.saveAndFlush(holiday);
		    	
		    	Holiday holiday2023 = new Holiday(holiday.getName(), holiday.getDate().plusYears(1), holiday.getObserved().plusYears(1), holiday.isActive());
		    	holidayRepo.saveAndFlush(holiday2023);
		    	
		    	Holiday holiday2024 = new Holiday(holiday.getName(), holiday.getDate().plusYears(2), holiday.getObserved().plusYears(2), holiday.isActive());
		    	holidayRepo.saveAndFlush(holiday2024);
		    }*/
		};
	}
    
	// Reference: https://www.baeldung.com/java-read-json-from-url
	// Reference: https://stackoverflow.com/questions/71788850/how-to-parse-a-json-array-of-objects-using-jackson
	public static Holiday[] getHolidaysFromAPI() throws IOException {
		URL url = new URL("https://holidayapi.com/v1/holidays?pretty&country=SG&year=2022&key=2872decf-d74d-4f8f-997b-822b41f2908a");
		ObjectMapper mapper = new ObjectMapper();
	    ObjectNode node = mapper.readValue(url, ObjectNode.class);
	    return mapper.treeToValue(node.get("holidays"), Holiday[].class);
	}
}
