package com.example.service;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.payloadresponse.LoginResponse;

public interface UserService {

	String addUser(UserDTO userDTO);

	LoginResponse LoginUser(LoginDTO loginDTO);
	
}
