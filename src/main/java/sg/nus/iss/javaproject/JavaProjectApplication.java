package sg.nus.iss.javaproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.repository.StaffRepository;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRun(StaffRepository staffRepo) {
		return args -> {
			System.out.println("Create some staff");
			Staff staff=new Staff("NoMan","designation");
			staff.setPassword("noman");
			staffRepo.save(staff);
		};	
	}
}
