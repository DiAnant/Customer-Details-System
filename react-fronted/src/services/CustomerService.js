import axios from 'axios';

const CUSTOMER_API_BASE_URL = "http://localhost:8080/api/v1/customers";

const CustomerService = {


    getCustomers: function(){
        return axios.get(CUSTOMER_API_BASE_URL);
    },

    addCustomer: function(customer){
        return axios.post(CUSTOMER_API_BASE_URL, customer);
    },

    getCustomerById: function(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId);
    },

    updateCustomer: function(customer, customerId){
        return axios.put(CUSTOMER_API_BASE_URL + '/' + customerId, customer);
    },

    deleteCustomer: function(customerId){
        return axios.delete(CUSTOMER_API_BASE_URL + '/' + customerId);
    },

    searchCustomers: function(parameter, query){
        return axios.get(CUSTOMER_API_BASE_URL + "/search/" + parameter + '/' + query);
    }
};

export default CustomerService;