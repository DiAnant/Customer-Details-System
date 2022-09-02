package com.cognizant.customer_detail_system.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.customer_detail_system.exception.ResourceNotFoundException;
import com.cognizant.customer_detail_system.model.Customer;
import com.cognizant.customer_detail_system.model.User;
import com.cognizant.customer_detail_system.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/v1")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean login(@RequestBody User userdata){   
        
        // we can eventually fetch it from the database or something
        User user1 = new User("anant", "password");
        
        if(!userdata.getUsername().equals(user1.getUsername()))
            return false;
        if(!userdata.getPassword().equals(user1.getPassword()))
            return false;
        return true;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public void createCustomer(@RequestBody Customer customer){
        customer.setCustomer_creation_date(LocalDate.now());
        customerRepository.save(customer); 
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No Customer Record Found For Id: " + id));
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer){
        
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No Customer Record Found For ID: " + id));

        customer.setFirst_name(updatedCustomer.getFirst_name());
        customer.setLast_name(updatedCustomer.getLast_name());
        customer.setEmail_id(updatedCustomer.getEmail_id());
        customer.setPhone_number(updatedCustomer.getPhone_number());

        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        Customer customer = customerRepository.findById(id).
        orElseThrow(() -> new ResourceNotFoundException(("No Customer Record Found For ID: " + id)));

        customerRepository.delete(customer);
        return ResponseEntity.ok("Customer Record Deleted Succesfully!");
    }

    @RequestMapping(value = "/customers/search/{parameter}/{value}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomersBySearching(@PathVariable String parameter,@PathVariable String value){

        List<Customer> searchResults;

        if(parameter.equals("name")){
            // firstName results are stored directly in searchResults
            searchResults = customerRepository.findByFirstNameContaining(value);
                List<Customer> lastNameResults = customerRepository.findByLastNameContaining(value);
                for(Customer c: lastNameResults)
                    searchResults.add(c);
            }
        else if(parameter.equals("phone_number")){
            searchResults = customerRepository.findByPhoneNumberContaining(value);
        }
        else{
            // parameter == email_id
            searchResults = customerRepository.findByEmailIdContaining(value);
        }

        return ResponseEntity.ok(searchResults);
    }
}