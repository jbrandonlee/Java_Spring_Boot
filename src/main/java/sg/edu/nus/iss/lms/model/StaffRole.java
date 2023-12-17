package sg.edu.nus.iss.lms.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class StaffRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String roleType;
	
	@ManyToMany(mappedBy="staffRole")
	private List<Staff> staff;
	
	public StaffRole() {}
	public StaffRole(String roleType) {
		super();
		this.roleType = roleType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "StaffRole [id=" + id + ", roleType=" + roleType + ", staff=" + staff + "]";
	}
}