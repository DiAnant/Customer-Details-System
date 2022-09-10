package com.cognizant.customer_detail_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.customer_detail_system.exception.ResourceNotFoundException;
import com.cognizant.customer_detail_system.model.CustomUserDetails;
import com.cognizant.customer_detail_system.model.User;
import com.cognizant.customer_detail_system.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // taking out user from the Optional
        User user = this.userRepository.findByUsername(username).
        orElseThrow(() -> new ResourceNotFoundException("User: " + username + 
        " not found in the database"));

        return new CustomUserDetails(user);
    }

    
}
