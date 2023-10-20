package com.example.demo.service;

import com.example.demo.REPO.UserRepository;
import com.example.demo.dto.request.LoginRequestDto;
import com.example.demo.dto.request.SignupRequestDto;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.model.User;
import com.example.demo.utils.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenService implements UserDetailsService {

    AuthenticationManager authenticationManager;


    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenHandler tokenHandler;

    public LoginResponse login(LoginRequestDto loginRequestDTO){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

        } catch (Exception e) {
//            throw new EntityNotFound("Username or password invalid");
            e.printStackTrace();
        }
        User user = (User) authentication.getPrincipal();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(user.getUsername());
        loginResponse.setRole(user.getRole());
        loginResponse.setToken(tokenHandler.generateToken(user));
        return loginResponse;
    }

    public User signUp(SignupRequestDto signUpRequestDTO){
        User user = new User();
        user.setUsername(signUpRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUsername(username);
        return user;
    }
}
