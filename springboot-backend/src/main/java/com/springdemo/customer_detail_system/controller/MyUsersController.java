package com.springdemo.customer_detail_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.customer_detail_system.model.User;
import com.springdemo.customer_detail_system.service.MyUsersService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class MyUsersController {

    @Autowired
    MyUsersService myUsersService;
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        return myUsersService.getAllUsers();
    }    

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){ 
        return myUsersService.createUser(user);
    }

    // only the owner of that account can change their password
    @RequestMapping(value = "/user/change-password", method = RequestMethod.POST)
    public ResponseEntity<String> changePassword(@RequestParam String password,
     @RequestParam String newPassword, @RequestParam String username){

        return myUsersService.changePassword(password, newPassword, username);
    }
    
    // only root user should be allowed to change roles
    @RequestMapping(value = "/user/change-role", method = RequestMethod.POST)
    public ResponseEntity<String> changeRole(@RequestParam String username, @RequestParam String role){
        return myUsersService.changeRole(username, role);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<Boolean> loginUser(@RequestParam String username, @RequestParam String password){
        return myUsersService.loginUser(username, password);
    }
}
