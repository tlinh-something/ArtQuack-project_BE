package com.example.demo.controller;

import com.example.demo.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    ResponseHandler responseHandler;
    @GetMapping("admin-only")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity getAdmin(){
        return responseHandler.response(200, "Successfully get data!", null);
    }

    @GetMapping("all-user")
    public ResponseEntity get(){
        return responseHandler.response(200, "Successfully get data!", null);
    }
}
