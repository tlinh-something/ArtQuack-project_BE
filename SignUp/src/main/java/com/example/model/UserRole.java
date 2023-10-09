package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserRole")
public class UserRole {
    @Id
    @Column(name = "userRoleID")
    private int userRoleID;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;

	public UserRole(Role role) {
		super();
		this.role = role;
	}

	public int getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

    // getters and setters
    
}
