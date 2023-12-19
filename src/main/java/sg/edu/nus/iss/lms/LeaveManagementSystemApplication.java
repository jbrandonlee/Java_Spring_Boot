package sg.edu.nus.iss.lms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
import sg.edu.nus.iss.lms.model.Overtime;
import sg.edu.nus.iss.lms.model.Overtime.ClaimStatus;
import sg.edu.nus.iss.lms.repository.AccountRepository;
import sg.edu.nus.iss.lms.repository.DepartmentRepository;
import sg.edu.nus.iss.lms.repository.EmployeeRepository;
import sg.edu.nus.iss.lms.repository.LeaveEntitlementRepository;
import sg.edu.nus.iss.lms.repository.LeaveRepository;
import sg.edu.nus.iss.lms.repository.LeaveTypeRepository;
import sg.edu.nus.iss.lms.repository.OvertimeRepository;

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
    						   OvertimeRepository overtimeRepo,
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
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 15), DaySection.AM, LocalDate.of(2023, 12, 16), DaySection.PM, "Holiday", "person1", "contact1", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, compensation, LocalDate.of(2023, 12, 28), DaySection.AM, LocalDate.of(2023, 12, 28), DaySection.AM, "Break", "person3", "contact3", LeaveStatus.REJECTED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 29), DaySection.AM, LocalDate.of(2023, 12, 29), DaySection.PM, "Holiday", "person4", "contact4", LeaveStatus.APPROVED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 30), DaySection.AM, LocalDate.of(2023, 12, 30), DaySection.AM, "Holiday", "updated1", "updated2", LeaveStatus.UPDATED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 30), DaySection.PM, LocalDate.of(2023, 12, 30), DaySection.PM, "Holiday", "cancelled1", "cancelled2", LeaveStatus.CANCELLED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2023, 12, 31), DaySection.AM, LocalDate.of(2023, 12, 31), DaySection.PM, "Holiday", "delete1", "delete2", LeaveStatus.DELETED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2024, 1, 30), DaySection.PM, LocalDate.of(2024, 1, 30), DaySection.PM, "Holiday", "2024-1", "2024-2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, annual, LocalDate.of(2024, 2, 10), DaySection.PM, LocalDate.of(2024, 2, 10), DaySection.PM, "Holiday", "2024-1", "2024-2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			leaveRepo.save(new Leave(brandonBoss, medical, LocalDate.of(2023, 12, 27), DaySection.AM, LocalDate.of(2023, 12, 27), DaySection.PM, "Sick", "person2", "contact2", LeaveStatus.APPLIED));
			overtimeRepo.save(new Overtime(brandonBoss, LocalDateTime.of(LocalDate.of(2023, 2, 1), LocalTime.of(18, 0)), LocalDateTime.of(LocalDate.of(2023, 2, 1), LocalTime.of(22, 0)), "4hrs OT", ClaimStatus.APPLIED));
			overtimeRepo.save(new Overtime(brandonBoss, LocalDateTime.of(LocalDate.of(2023, 2, 2), LocalTime.of(18, 30)), LocalDateTime.of(LocalDate.of(2023, 2, 3), LocalTime.of(0, 0)), "5.5hrs OT", ClaimStatus.REJECTED));
			overtimeRepo.save(new Overtime(brandonBoss, LocalDateTime.of(LocalDate.of(2023, 2, 3), LocalTime.of(9, 0)), LocalDateTime.of(LocalDate.of(2023, 2, 3), LocalTime.of(18, 0)), "9hours OT", ClaimStatus.APPROVED));
			
			Employee brandonManager = empRepo.save(new Employee("BrandonManager","Manager", finance));
			Employee brandonStaff = empRepo.save(new Employee("BrandonStaff","AdminStaff", finance));
			Employee alexStaff = empRepo.save(new Employee("AlexStaff","ProfessionalStaff", sales));
		};
	}
}
