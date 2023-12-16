package sg.edu.nus.iss.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.repository.AccountRepository;

@SpringBootApplication
public class LeaveManagementSystemApplication {

	@Autowired
	AccountRepository accRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRun(AccountRepository accountRepo) {
		return args -> {
			accRepo.save(new Account("E001", "brandon", "password1"));
		};
	}
}
