package com.example.One.Service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.One.DTO.UserDTO;
import com.example.One.Entity.User;
import com.example.One.Repo.UserRepo;
import com.example.One.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String addUser(UserDTO userDTO) {
		User user = new User(
				userDTO.getUserName(),
				userDTO.getUserRole(),
				userDTO.getId(),
				userDTO.getName(),
				userDTO.getEmail(),
				this.passwordEncoder.encode(userDTO.getPassword()),
				userDTO.isStatus()
				);
		userRepo.save(user);
		
		return user.getUserName();
	}
 
}
