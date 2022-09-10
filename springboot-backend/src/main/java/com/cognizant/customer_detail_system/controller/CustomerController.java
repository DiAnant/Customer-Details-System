package com.cognizant.customer_detail_system.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.customer_detail_system.model.Customer;
import com.cognizant.customer_detail_system.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer){
        return customerService.updateCustomer(id, updatedCustomer);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

    @RequestMapping(value = "/customers/search/{parameter}/{value}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomersBySearching(@PathVariable String parameter,@PathVariable String value){
        return customerService.getCustomersBySearching(parameter, value);
    }
}