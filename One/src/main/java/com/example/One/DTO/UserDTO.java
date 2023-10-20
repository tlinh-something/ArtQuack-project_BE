package com.example.One.DTO;

import com.example.One.Entity.UserRole;

public class UserDTO {
	private String userName;
	private UserRole userRole;
	private int id;
    private String name;
    private String email;
    private String password;
    private boolean status;
    
    public UserDTO() {
    	super();
    	
    }

	public UserDTO(String userName, UserRole userRole, int id, String name, String email, String password,
			boolean status) {
		super();
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
		return "UserDTO [userName=" + userName + ", userRole=" + userRole + ", id=" + id + ", name=" + name + ", email="
				+ email + ", password=" + password + ", status=" + status + "]";
	}

}
