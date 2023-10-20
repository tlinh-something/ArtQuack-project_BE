package com.example.demo.controller;

import com.example.demo.dto.request.LoginRequestDto;
import com.example.demo.dto.request.SignupRequestDto;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.model.User;
import com.example.demo.service.AuthenService;
import com.example.demo.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authentication {
    @Autowired
    AuthenService authenService;

    @Autowired
    ResponseHandler responseHandler;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDTO){
        LoginResponse user = authenService.login(loginRequestDTO);
        return responseHandler.response(200, "Login success!", user);
    }


    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignupRequestDto signUpRequestDTO){
        User user = authenService.signUp(signUpRequestDTO);
        return responseHandler.response(200, "Sign Up success!", user);
    }
}
