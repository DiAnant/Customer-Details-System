import {useState, useEffect} from 'react';
import CustomerService from '../services/CustomerService';
import { useNavigate, useParams } from 'react-router-dom';


function AddOrUpdateCustomer(){

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [emailId, setEmailId] = useState("");

    let navigate = useNavigate();
    const {id} = useParams();
    
    useEffect(() => {

        if(id === 'add')
            return;

        CustomerService.getCustomerById(id).then(res => {
            let customer = res.data;
            setFirstName(customer.first_name);
            setLastName(customer.last_name);
            setPhoneNumber(customer.phone_number);
            setEmailId(customer.email_id);
        });
    }, [id]);
    
    function firstNameHandler(event){
        setFirstName(event.target.value);
    }
    function lastNameHandler(event){
        setLastName(event.target.value);
    }
    function phoneNumberHandler(event){
        setPhoneNumber(event.target.value);
    }
    function emailIdHandler(event){
        setEmailId(event.target.value);
    }
    
    function addOrUpdateCustomer(event){
        event.preventDefault();
        const customer = {
            "first_name": firstName,
            "last_name": lastName,
            "email_id": emailId,
            "phone_number": phoneNumber
        }

        if(id === 'add'){
            CustomerService.addCustomer(customer).then(res => {
                navigate("/customers");
            });    
        }
        else{
            CustomerService.updateCustomer(customer, id).then(res => {
                navigate("/customers");
            });    
        }
    }

    function cancel(){
        navigate("/");
    }

    return(
        <div className="container">
            <div className="row">
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center m-2">{id === 'add' ? 'Add' : 'Update'} Customer</h3>
                    <div className="card-body">
                        <form>

                            <div className="form-group m-2">
                                <label>First Name</label>
                                <input placeholder="First Name" name="first_name" 
                                className="form-control my-1" value={firstName} 
                                onChange={firstNameHandler} />
                            </div>

                            <div className="form-group m-2">
                                <label>Last Name</label>
                                <input placeholder="Last Name" name="last_name" 
                                className="form-control my-1" value={lastName} 
                                onChange={lastNameHandler} />
                            </div>

                            <div className="form-group m-2">
                                <label>Phone Number</label>
                                <input placeholder="Phone Number" name="phone_number" 
                                className="form-control my-1" value={phoneNumber} 
                                onChange={phoneNumberHandler} />
                            </div>

                            <div className="form-group m-2">
                                <label>Email Address</label>
                                <input placeholder="Email Address" name="email_id" 
                                className="form-control my-1" value={emailId} 
                                onChange={emailIdHandler} />
                            </div>

                            <button className='btn btn-success m-2' onClick={addOrUpdateCustomer}>Save</button> 
                            <button className='btn btn-danger m-2' onClick={cancel}>Cancel</button> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddOrUpdateCustomer;