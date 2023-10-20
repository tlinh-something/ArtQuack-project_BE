package com.example.One.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	
	@Id
    @Column(name = "userName")
    private String userName;
	
	@ManyToOne
	@JoinColumn(name = "userRoleID")
	private UserRole userRole;
	
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
	
 

    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "status")
    private boolean status;
    
    public User() { 	
    	
    }

	public User(String userName, com.example.One.Entity.UserRole userRole, int id, String name, String email,
			String password, boolean status) {
		this.userName = userName;
		this.userRole = userRole;
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userRole=" + userRole + ", id=" + id + ", name=" + name + ", email="
				+ email + ", password=" + password + ", status=" + status + "]";
	}
	
	
    
    

}
