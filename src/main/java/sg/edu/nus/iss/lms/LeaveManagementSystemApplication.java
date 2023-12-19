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

}
