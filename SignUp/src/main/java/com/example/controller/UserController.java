package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.payloadresponse.LoginResponse;
import com.example.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/login")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping(path = "/save")
	public String saveUser(@RequestBody UserDTO userDTO) {
		String id = userService.addUser(userDTO);
		return id;
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
		LoginResponse loginResponse = userService.LoginUser(loginDTO);
		return ResponseEntity.ok(loginResponse);
	}
	

}
