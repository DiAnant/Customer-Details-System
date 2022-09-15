package com.springdemo.customer_detail_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springdemo.customer_detail_system.exception.BadCredentialsException;
import com.springdemo.customer_detail_system.exception.ResourceAlreadyExistsException;
import com.springdemo.customer_detail_system.model.User;
import com.springdemo.customer_detail_system.repository.UserRepository;

@Service
public class MyUsersService {

    @Autowired
    private UserRepository myUserRepository;
    
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(myUserRepository.findAll());
    }

    public ResponseEntity<String> createUser(User user) throws ResourceAlreadyExistsException{
        // if entered username already exists in the database, throw exception
        if(myUserRepository.existsByUsername(user.getUsername())){
            throw new ResourceAlreadyExistsException("A User with username: " + user.getUsername() +
             " already exists in the database. Please choose a different username.");
        }

        // before saving user into the database, we will encrypt the password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        myUserRepository.save(user);
        return ResponseEntity.ok("User: " + user.getUsername() + " Successfully Registered");
    }

    public ResponseEntity<String> changePassword(String password, String newPassword, String username){
        
        // if this API is being called it means that username definitely exists and hence we don't need
        // to check for that scenario. But we need to check if password is correct or not.

        User user = myUserRepository.findByUsername(username).get();
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect Current Password. Please enter correct" + 
            "current password of this account in order to reset password.");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        myUserRepository.save(user);

        return ResponseEntity.ok("Password successfully changed for User: " + username);
    }
}
