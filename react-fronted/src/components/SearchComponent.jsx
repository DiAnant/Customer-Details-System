import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import CustomerService from '../services/CustomerService';

export default function SearchComponent() {

    const [parameter, setParameter] = useState("name");
    const [query, setQuery] = useState("");
    const [customers, setCustomers] = useState([]);
    const [noResultsFoundMessage, setNoResultsFoundMessage] = useState("");
    let navigate = useNavigate();

    const queryHandler = function(event){
        setQuery(event.target.value);
    };

    const parameterHandler = function(event){
        setParameter(event.target.value);
    }

    const searchCustomer = function(){
        CustomerService.searchCustomers(parameter, query).then(res => {
            setCustomers(res.data);
            if(customers.length === 0)
                setNoResultsFoundMessage("No Results Found For Your Query. Try Again!");
        });
    };

    const cancel = function(){
        navigate("/customers/");
    };

    return (
        <div className='container'>

            <h1 className='text-center my-5'>Search Customers</h1>

            <div className='container w-50'>
                <form>
                <div className="form-group my-3">
                        <label>Search Query Type</label>
                        <select className="form-control" value={parameter} onChange={parameterHandler}>
                            <option value="name">Name</option>
                            <option value="phone_number">Phone Number</option>
                            <option value="email_id">Email Address</option>
                        </select>
                    </div>
                    <div className="form-group my-3">
                        <label for="exampleFormControlInput1">Enter Query</label>
                        <input type="email" className="form-control" value={query} onChange={queryHandler} placeholder="Enter your query here" />
                    </div>
                </form>
                <div className='text-center'>
                    <button className='btn btn-primary m-3' onClick={searchCustomer}>SEARCH</button>
                    <button className='btn btn-danger m-3' onClick={cancel}>CANCEL</button>
                </div>
            </div>

            <div className='my-5'>  
            {
                customers.length > 0 ? 
                <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email ID</th>
                        <th>Phone Number</th>
                        <th>Entry Date</th>
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
                            </tr>
                        )
                    }
                </tbody>
            </table>
            : <h3 className='text-center my-5 display-4'>{noResultsFoundMessage}</h3>
            }
            </div>

        </div>
    );
}
