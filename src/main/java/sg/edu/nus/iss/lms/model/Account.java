package sg.edu.nus.iss.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
	private String id;
	
	@Size(min=3, max=20, message = "Username must be 3-20 characters")
	private String username;
	
	@Size(min=6, message = "Password must be at least 6 characters")	
	private String password;
	
	// private Employee employee;
	
	// private List<Role> roles;
}