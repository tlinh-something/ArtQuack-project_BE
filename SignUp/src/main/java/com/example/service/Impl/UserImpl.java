package com.example.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.payloadresponse.LoginResponse;
import com.example.repo.UserRepo;
import com.example.service.UserService;

@Service
public class UserImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String addUser(UserDTO userDTO) {
		
		User user = new User(userDTO.getUserName(), 
							 userDTO.getUserRole(), 
							 userDTO.getId(), 
							 userDTO.getName(), 
							 userDTO.getEmail(), 
							 this.passwordEncoder.encode(userDTO.getPassword()), 
							 userDTO.isStatus());
		userRepo.save(user);
		return user.getUserName();
	}

	@Override
	public LoginResponse LoginUser(LoginDTO loginDTO) {
		
		String msg = "";
		User user1 = userRepo.findByEmail(loginDTO.getEmail());
		if(user1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = user1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if(isPwdRight) {
				Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
				if(user.isPresent()) {
					return new LoginResponse("Login Success", true);
				}else {
					return new LoginResponse("Login Failed", false);
				}
			}else {
				return new LoginResponse("Password Not Matched", false);
			}
		}else {
			return new LoginResponse("Email Not Exist", false);
		}
		
	}

}
