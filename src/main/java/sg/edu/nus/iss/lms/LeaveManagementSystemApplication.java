package sg.edu.nus.iss.lms;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Department;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.model.Leave.DaySection;
import sg.edu.nus.iss.lms.model.Leave.LeaveStatus;
import sg.edu.nus.iss.lms.model.LeaveEntitlement;
import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.repository.AccountRepository;
import sg.edu.nus.iss.lms.repository.DepartmentRepository;
import sg.edu.nus.iss.lms.repository.EmployeeRepository;
import sg.edu.nus.iss.lms.repository.LeaveEntitlementRepository;
import sg.edu.nus.iss.lms.repository.LeaveRepository;
import sg.edu.nus.iss.lms.repository.LeaveTypeRepository;

@SpringBootApplication
public class LeaveManagementSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementSystemApplication.class, args);
	}

    @Bean
    CommandLineRunner loadData(AccountRepository accRepo,
    						   EmployeeRepository empRepo,
    						   LeaveRepository leaveRepo,
    						   LeaveTypeRepository leaveTypeRepo,
    						   LeaveEntitlementRepository leaveEntitlementRepo,
    						   DepartmentRepository departmentRepo) {
		return args -> {
			// Initialize Departments
			Department finance = departmentRepo.save(new Department("Finance"));
			Department sales = departmentRepo.save(new Department("Sales"));

			// Initialize LeaveTypes
			LeaveType annual = leaveTypeRepo.save(new LeaveType("Annual"));
			LeaveType medical = leaveTypeRepo.save(new LeaveType("Medical"));
			LeaveType compensation = leaveTypeRepo.save(new LeaveType("Compensation"));
			
			// Create Employee Account with Entitlement & Leaves
			Employee brandonBoss = empRepo.save(new Employee("Lee Junhui Brandon","Boss", finance));
			accRepo.save(new Account("brandon", "password1", brandonBoss));
			leaveEntitlementRepo.save(new LeaveEntitlement(brandonBoss, annual, 18));
			leaveEntitlementRepo.save(new LeaveEntitlement(brandonBoss, medical, 60));
			leaveEntitlementRepo.save(new LeaveEntitlement(brandonBoss, compensation, 0));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 21), DaySection.AM, LocalDate.of(2023, 12, 26), DaySection.PM, "Holiday", "person1", "contact1", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, compensation, LocalDate.of(2023, 12, 28), DaySection.AM, LocalDate.of(2023, 12, 28), DaySection.AM, "Break", "person3", "contact3", LeaveStatus.REJECTED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 29), DaySection.AM, LocalDate.of(2023, 12, 29), DaySection.PM, "Holiday", "person4", "contact4", LeaveStatus.APPROVED));
			
			Employee brandonManager = empRepo.save(new Employee("BrandonManager","Manager", finance));
			Employee brandonStaff = empRepo.save(new Employee("BrandonStaff","AdminStaff", finance));
			Employee alexStaff = empRepo.save(new Employee("AlexStaff","ProfessionalStaff", sales));
		};
	}
}
