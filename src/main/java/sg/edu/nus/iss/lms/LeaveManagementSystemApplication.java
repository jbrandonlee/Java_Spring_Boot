package sg.edu.nus.iss.lms;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.ApprovalStatus;
import sg.edu.nus.iss.lms.model.LeaveApplication;
import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.model.StaffRole;
import sg.edu.nus.iss.lms.model.Staff;
import sg.edu.nus.iss.lms.repository.AccountRepository;
import sg.edu.nus.iss.lms.repository.LeaveApplicationRepository;
import sg.edu.nus.iss.lms.repository.StaffRepository;

@SpringBootApplication
public class LeaveManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementSystemApplication.class, args);
	}

//	@Bean
//	CommandLineRunner loadData(LeaveApplicationRepository leaveRepo, 
//			RoleRepository roleRepo,
//            StaffRepository staffRepo,
//            AccountRepository accountRepo) {
//		
//return (args) -> {
//	//add 3 roles
//	StaffRole adminRole = roleRepo.save(new StaffRole("Administrator", "System Administrator"));
//	
//	
//	
//	
//	
//	
	
//	Role adminRole = roleRepo.save(new Role("Administrator", "System Administrator"));
//    Role staffRole = roleRepo.save(new Role("Manager", "Manager"));
//    Role managerRole = roleRepo.save(new Role("Staff", "Staff"));
//    
//    //add users
//    Account account1 = new Account("brandon","123456");
//    Account account2 = new Account("diaz","123456");
//    Account account3 = new Account("luke","123456");
//    Account account4 = new Account("yh","123456");
//    
//    accountRepo.save(account1);
//    accountRepo.save(account2);
//    accountRepo.save(account3);
//    accountRepo.save(account4);
//    
//    //add staffs
//    Staff staff1 = new Staff("Brandon","Manager","gg@gmail.com");
//    Staff staff2 = new Staff("Diaz","Staff","hh@gmail.com",1);
//    Staff staff3 = new Staff("Luke","Staff","yy@gmail.com",1);
//    Staff staff4 = new Staff("YH","Staff","zz@gmail.com");
//    staff1.setAccount(account1);
//    staff2.setAccount(account2);
//    staff3.setAccount(account3);
//    staff4.setAccount(account4);
//    
//    staffRepo.save(staff1);
//    staffRepo.save(staff2);
//    staffRepo.save(staff3);
//    staffRepo.save(staff4);
//    
//    //add leaves
//    LeaveApplication leave1 = new LeaveApplication();
//    leave1.setLeaveType(LeaveTypeEnum.Annual);
//    leave1.setLeaveApprovalStatus(ApprovalStatusEnum.Applied);
//    leave1.setLeaveStartDate(LocalDate.now().minusDays(10));
//    leave1.setLeaveEndDate(LocalDate.now());
//    leave1.setStaff(staff2);
//    leave1.setDestination("des");
//    leave1.setLeaveReasons("trip");
//    leave1.setWorkDissemination("gg");
//    
//    LeaveApplication leave2 = new LeaveApplication();
//    leave2.setLeaveType(LeaveTypeEnum.Medical);
//    leave2.setLeaveApprovalStatus(ApprovalStatusEnum.Approved);
//    leave2.setLeaveStartDate(LocalDate.now().minusMonths(2));
//    leave2.setLeaveEndDate(LocalDate.now().minusMonths(1));
//    leave2.setStaff(staff3);
//    leave2.setDestination("des");
//    leave2.setLeaveReasons("trip");
//    leave2.setWorkDissemination("gg");
//    
//    LeaveApplication leave3 = new LeaveApplication();
//    leave3.setLeaveType(LeaveTypeEnum.Annual);
//    leave3.setLeaveApprovalStatus(ApprovalStatusEnum.Applied);
//    leave3.setLeaveStartDate(LocalDate.now());
//    leave3.setLeaveEndDate(LocalDate.now().plusDays(7));
//    leave3.setStaff(staff4);
//    leave3.setDestination("des");
//    leave3.setLeaveReasons("trip");
//    leave3.setWorkDissemination("gg");
//    
//    leaveRepo.save(leave1);
//    leaveRepo.save(leave2);
//    leaveRepo.save(leave3);
//	};
//}
	

}
