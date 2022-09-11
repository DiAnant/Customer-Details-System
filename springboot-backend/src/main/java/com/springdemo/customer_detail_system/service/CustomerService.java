package com.springdemo.customer_detail_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springdemo.customer_detail_system.exception.ResourceAlreadyExistsException;
import com.springdemo.customer_detail_system.exception.ResourceNotFoundException;
import com.springdemo.customer_detail_system.model.Customer;
import com.springdemo.customer_detail_system.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    public ResponseEntity<String> createCustomer(Customer customer){

        // check if phone or email is not already present in the database
        ifPhoneorEmailAreadyPresent_Create(customer);

        customer.setCustomer_creation_date(LocalDate.now());
        customerRepository.save(customer); 
        return ResponseEntity.ok("Customer Record Saved Successfully!");
    }

    public ResponseEntity<Customer> getCustomerById(Long id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No Customer Record Found For Id: " + id));
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<String> updateCustomer(Long id, Customer updatedCustomer) throws ResourceNotFoundException{

        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No Customer Record Found For ID: " + id));

        // check if phone or email is not already present in the database
        ifPhoneorEmailAreadyPresent_Update(customer, updatedCustomer);
        
        customer.setFirst_name(updatedCustomer.getFirst_name());
        customer.setLast_name(updatedCustomer.getLast_name());
        customer.setEmail_id(updatedCustomer.getEmail_id());
        customer.setPhone_number(updatedCustomer.getPhone_number());

        customerRepository.save(customer);
        return ResponseEntity.ok("Customer Record Updated Successfully");
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

    private void ifPhoneorEmailAreadyPresent_Create(Customer customer){
        checkDuplicateEmail(customer.getEmail_id());
        checkDuplicatePhone(customer.getPhone_number());
    }

    private void ifPhoneorEmailAreadyPresent_Update(Customer customer, Customer updatedCustomer){
        if(!customer.getEmail_id().equals(updatedCustomer.getEmail_id())){
            // check if this newly entered email_id is not a duplicate of anyone else's in the DB 
            checkDuplicateEmail(updatedCustomer.getEmail_id());
        }

        if(!customer.getPhone_number().equals(updatedCustomer.getPhone_number())){
            // check if this newly entered phone_number is not a duplicate of anyone else's in the DB 
            checkDuplicatePhone(updatedCustomer.getPhone_number());
        }        
    }

    private void checkDuplicateEmail(String emailId){
        if(customerRepository.existsByEmailId(emailId)){
            throw new ResourceAlreadyExistsException("There already exists a record" + 
            " with this email-address: " + emailId +". Please enter a new email.");
        }
    }

    private void checkDuplicatePhone(String phoneNumber){
        if(customerRepository.existsByPhoneNumber(phoneNumber)){
            throw new ResourceAlreadyExistsException("There already exists a record" + 
            " with this phone-number: " + phoneNumber +". Please enter a new phone number.");
        }    
    }
}
