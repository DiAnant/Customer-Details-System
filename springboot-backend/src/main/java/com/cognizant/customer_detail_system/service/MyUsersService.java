package com.cognizant.customer_detail_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.customer_detail_system.model.User;
import com.cognizant.customer_detail_system.repository.UserRepository;

@Service
public class MyUsersService {

    @Autowired
    private UserRepository myUserRepository;
    
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(myUserRepository.findAll());
    }

    public ResponseEntity<String> createUser(User user){
        myUserRepository.save(user);
        return ResponseEntity.ok("User: " + user.getUsername() + " Successfully Registered");
    }
}
