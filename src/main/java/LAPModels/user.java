package LAPModels;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userId;
	
		private String userName;
		private String employeeId;
		private String password;

		@OneToMany(mappedBy = "user")
		private List<role> userRoles;
		
		public user() {};
		public user(String userName, String employeeId,String password ) {
			this.userName=userName;
			this.employeeId=employeeId;
			this.password=password;
			
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
