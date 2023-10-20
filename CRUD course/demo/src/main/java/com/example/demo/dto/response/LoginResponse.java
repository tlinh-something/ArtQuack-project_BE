package com.example.demo.dto.response;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class LoginResponse extends User {
    String token;
}
