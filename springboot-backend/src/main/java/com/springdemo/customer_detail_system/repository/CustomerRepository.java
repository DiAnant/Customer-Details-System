package com.springdemo.customer_detail_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdemo.customer_detail_system.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    public List<Customer> findByFirstNameContaining(String keyword);
    public List<Customer> findByLastNameContaining(String keyword);
    public List<Customer> findByPhoneNumberContaining(String keyword);
    public List<Customer> findByEmailIdContaining(String keyword);
    public boolean existsByEmailId(String email_id);
    public boolean existsByPhoneNumber(String phone_number);
}
