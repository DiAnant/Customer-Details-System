import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom';
import LoginService from '../services/LoginService';

export default function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [triedToLoginYet, setTriedToLoginYet] = useState(false);
    const [loginStatus, setLoginStatus] = useState(false);
    let navigate = useNavigate();

    const handleUsernameChange = function(event){
        setUsername(event.target.value);
    }

    const handlePasswordChange = function(event){
        setPassword(event.target.value);
    }


    const handleLogin = function(event){        

        event.preventDefault();
        setTriedToLoginYet(true);

        LoginService.login(username, password).then(res => {
            if(res.data === true){
                setLoginStatus(true);
                navigate("/customers");
            }
        });
    }

    const cardStyle = {
        "width": "18rem",
        "backgroundColor": "#FFF1FA",
    }
    const divStyle = {
        "margin": "auto",
    }

  return (
    <div style={divStyle}>
        <div className="card" style={cardStyle}>
            <div className="card-body">
                <h5 className='m-3 card-title text-center'>Enter your credentials</h5>
                <form>
                    <div className="form-group m-2">
                        <label>Username address</label>
                        <input type="username" className="form-control" onChange={handleUsernameChange} placeholder="Enter username" value={username}/>
                    </div>
                    <div className="form-group m-2">
                        <label>Password</label>
                        <input type="password" className="form-control" onChange={handlePasswordChange} placeholder="Password" value={password}/>
                    </div>
                    <div className="text-center">
                        <button type='button' className="btn m-2 btn-sm btn-danger" onClick={handleLogin} >Login</button>
                    </div>
                </form>
            </div>
        </div>

        {triedToLoginYet && !loginStatus ? <h5 className='text-center mt-5'>Please Enter Correct Credentials</h5> : <span></span>}
    </div>
  )
};
