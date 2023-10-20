package com.example.One.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.One.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findOneByEmailAndPassword(String email, String password);
	
	User findByUserName(String userName);
	

}
