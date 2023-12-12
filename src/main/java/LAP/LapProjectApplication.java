package LAP;

import org.hibernate.mapping.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import LAPModels.leaveApplication;
import LAPRepository.leaveRepository;

@SpringBootApplication
public class LapProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LapProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRun(leaveRepository leaveRepo) { 
		return args -> {
			leaveRepo.save(new leaveApplication(1,
				123, "anual leave",2023-01-01,2023-02-01,
				"visit family","a","321","APPLIED","ok"));
			List<leaveApplication> myApplications = leaveRepo.findAll();
			myApplications.forEach(myApplication -> System.out.println(myApplication));
	};
  }
}
