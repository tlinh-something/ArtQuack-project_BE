package com.example.One.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.One.DTO.UserDTO;
import com.example.One.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/save")
	public String saveUser(@RequestBody UserDTO userDTO) {
		String id =  userService.addUser(userDTO);
		return id;
	}

}
