package com.springdemo.customer_detail_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springdemo.customer_detail_system.exception.BadCredentialsException;
import com.springdemo.customer_detail_system.exception.ForbiddenActionException;
import com.springdemo.customer_detail_system.exception.ResourceAlreadyExistsException;
import com.springdemo.customer_detail_system.exception.ResourceNotFoundException;
import com.springdemo.customer_detail_system.model.User;
import com.springdemo.customer_detail_system.repository.UserRepository;

@Service
public class MyUsersService {

    @Autowired
    private UserRepository myUserRepository;
    
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = myUserRepository.findAll();
        // making the password useless, as we don't want to send it to client (even hashed password)
        userList.forEach(user -> user.setPassword("----"));
        return ResponseEntity.ok(userList);
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
        
        // validating username
        User user = myUserRepository.findByUsername(username).
        orElseThrow(() -> new ResourceNotFoundException("No user with username: " + username +
        " exists in the database. Please choose a valid user"));

        // validating password
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect Current Password. Please enter correct" + 
            "current password of this account in order to reset password.");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        myUserRepository.save(user);

        return ResponseEntity.ok("Password successfully changed for User: " + username);
    }

    public ResponseEntity<String> changeRole(String username, String role){

        // validating username
        User user = myUserRepository.findByUsername(username).
        orElseThrow(() -> new ResourceNotFoundException("No user with username: " + username +
        " exists in the database. Please choose a valid user"));

        // checking if username is not "root"
        if(username.equals("user_root")){
            throw new ForbiddenActionException("You cannot change ROLE for the root user.");
        }

        // validating role
        if(role.equals("ROOT")){
            throw new ForbiddenActionException("ROOT is a reserved role and cannot be" +
             " assigned to any one else.");
        }

        if(!role.equals("ADMIN") && !role.equals("EMPLOYEE")){
            throw new ForbiddenActionException("Please choose a role from the given options," +
             " ADMIN & EMPLOYEE. Entered role: " + role + " is an incorrect choice");
        }

        user.setRole(role);
        myUserRepository.save(user);
        return ResponseEntity.ok("Role changed successfully to " + role + " for user - " + username);
    }

    public ResponseEntity<Boolean> loginUser(String username, String password){
        
        // validating username
        User user = myUserRepository.findByUsername(username).
        orElseThrow(() -> new BadCredentialsException("No username: " + username + " found. Please enter a correct username!"));
        
        // validating password
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect Password. Please enter correct password to login!");
        }        

        return ResponseEntity.ok(true);
    }
}
