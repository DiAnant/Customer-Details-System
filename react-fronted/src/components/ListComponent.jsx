import React, {useState, useEffect} from "react";
import CustomerService from "../services/CustomerService";
import {useNavigate} from 'react-router-dom';

function ListComponent(props){

    const [customers, setCustomers] = useState([]);
    let navigate = useNavigate();

    const addCustomer = function(){
        navigate("/add-update-customer/add");
    }

    const searchCustomers = function(){
        navigate("/search-customers/");
    }

    const deleteCustomer = function(customerId){
        CustomerService.deleteCustomer(customerId).then(res => {
            let newCustomers = customers.filter(c => c.id !== customerId);
            setCustomers(newCustomers);
        });
    }

    useEffect(() => {
        CustomerService.getCustomers().then((res) => {
            setCustomers(res.data);
        });    
    }, []);

    return(
        <div className="mb-5">
            <h2 className="text-center pt-3">Customer List</h2>
            <button className="btn btn-primary m-2" onClick={addCustomer}>Add Customer</button>
            <button className="btn btn-warning m-2" onClick={searchCustomers}>Search Customers</button>
            <div className="row">
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email ID</th>
                            <th>Phone Number</th>
                            <th>Entry Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            customers.map(
                                customer => 
                                <tr key={customer.id}>
                                    <td>{customer.id}</td>
                                    <td>{customer.first_name}</td>
                                    <td>{customer.last_name}</td>
                                    <td>{customer.email_id}</td>
                                    <td>{customer.phone_number}</td>
                                    <td>{customer.customer_creation_date.slice(0,10)}</td>
                                    <td>
                                        <button className="btn btn-info btn-sm" onClick={() => {navigate(`/add-update-customer/${customer.id}`)}}>UPDATE</button>
                                        <button className="btn btn-danger btn-sm mx-3" onClick={() => deleteCustomer(customer.id)}>DELETE</button>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );

};

export default ListComponent;