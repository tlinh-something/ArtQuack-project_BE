package com.swp.ArtQuack.service;

import com.swp.ArtQuack.entity.Admin;
import com.swp.ArtQuack.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.repository.InstructorRepository;
import com.swp.ArtQuack.repository.LearnerRepository;

@Service
public class AdminService {

	@Autowired
    private AdminRepository adminReposervice;

    public Admin login (String username, String password){
        return adminReposervice.findByUsernameAndPassword(username,password);
    }

}
