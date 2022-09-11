package com.springdemo.customer_detail_system.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // can't say I completely understand this
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", length = 10, unique = true)
    private String phoneNumber;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "customer_creation_date")
    private LocalDate customerCreationDate;

    public Customer(){

    }

    public Customer(long id, String first_name, String last_name, String phone_number, String email_id,
    LocalDate customer_creation_date) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.phoneNumber = phone_number;
        this.emailId = email_id;
        this.customerCreationDate = customer_creation_date;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirst_name() {
        return firstName;
    }
    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }
    public String getLast_name() {
        return lastName;
    }
    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }
    public String getPhone_number() {
        return phoneNumber;
    }
    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }
    public String getEmail_id() {
        return emailId;
    }
    public void setEmail_id(String email_id) {
        this.emailId = email_id;
    }
    public LocalDate getCustomer_creation_date() {
        return customerCreationDate;
    }
    public void setCustomer_creation_date(LocalDate customer_creation_date) {
        this.customerCreationDate = customer_creation_date;
    }

    
}
