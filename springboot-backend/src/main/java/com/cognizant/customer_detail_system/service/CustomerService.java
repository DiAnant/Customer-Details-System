package com.cognizant.customer_detail_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.customer_detail_system.exception.ResourceNotFoundException;
import com.cognizant.customer_detail_system.model.Customer;
import com.cognizant.customer_detail_system.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    public ResponseEntity<String> createCustomer(Customer customer){
        customer.setCustomer_creation_date(LocalDate.now());
        customerRepository.save(customer); 
        return ResponseEntity.ok("Customer Record Saved Successfully!");
    }

    public ResponseEntity<Customer> getCustomerById(Long id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No Customer Record Found For Id: " + id));
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Customer> updateCustomer(Long id, Customer updatedCustomer) throws ResourceNotFoundException{
        
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No Customer Record Found For ID: " + id));

        customer.setFirst_name(updatedCustomer.getFirst_name());
        customer.setLast_name(updatedCustomer.getLast_name());
        customer.setEmail_id(updatedCustomer.getEmail_id());
        customer.setPhone_number(updatedCustomer.getPhone_number());

        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<String> deleteCustomer(Long id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id).
        orElseThrow(() -> new ResourceNotFoundException(("No Customer Record Found For ID: " + id)));

        customerRepository.delete(customer);
        return ResponseEntity.ok("Customer Record Deleted Succesfully!");
    }

    public ResponseEntity<List<Customer>> getCustomersBySearching(String parameter,String value){

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
