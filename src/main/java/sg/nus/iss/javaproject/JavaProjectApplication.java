package sg.nus.iss.javaproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.javaproject.model.Account;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.repository.AccountRepository;
import sg.nus.iss.javaproject.repository.StaffRepository;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}
	
	
//	 @Bean public CommandLineRunner commandLineRun(AccountRepository
//			 	accountRepo,StaffRepository staffRepo) 
//	 	{ return args -> {
//			 System.out.println("Create some staff"); 
//			 Staff staff=new Staff("NoMan","designation"); 
//			 Account account=new Account("Noman","noMan");
//			 staff.setPosition("administrative");
//			 staff.setAccount(account); 
//			 accountRepo.save(account); 
//			 staffRepo.save(staff);
//			 }; 
//	 }
	 
}
