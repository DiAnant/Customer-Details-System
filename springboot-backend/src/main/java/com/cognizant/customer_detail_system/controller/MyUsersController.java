package com.cognizant.customer_detail_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.customer_detail_system.model.User;
import com.cognizant.customer_detail_system.service.MyUsersService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class MyUsersController {

    @Autowired
    MyUsersService myUsersService;
    
    // this API should later be deleted, serves no purpose
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        return myUsersService.getAllUsers();
    }    

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){ 
        return myUsersService.createUser(user);
    }
}
