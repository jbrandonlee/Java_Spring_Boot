package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String roleName;
  private String department;
  private String password;
  
  // Default constructor
  public Role() {}

  // Constructor with parameters
  public Role(String roleName, String department, String password) {
    this.roleName = roleName;
    this.department = department;
    this.password = password;
  }
  
  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    return "Role [id=" + id + ", roleName=" + roleName + 
        ", department=" + department + ", password=" + password + "]";
  }
}
