package com.cognizant.customer_detail_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.customer_detail_system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(String username);
}
