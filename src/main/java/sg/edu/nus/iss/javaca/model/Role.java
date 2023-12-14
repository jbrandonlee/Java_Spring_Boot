package sg.edu.nus.iss.javaca.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Role")
public class Role {
	
	// attributes
	@Id
	@Column(name = "RoleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;
	
	@ManyToMany(mappedBy="roles")
	private List<Employee> employees;

	public Role() {
	}

	public Role(int roleId) {
		this.roleId = roleId;
	}

	public Role(int roleId, String name, String description) {
		this.roleId = roleId;
		this.setName(name);
		this.setDescription(description);
	}

	public int getRoleId() {
		return roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
